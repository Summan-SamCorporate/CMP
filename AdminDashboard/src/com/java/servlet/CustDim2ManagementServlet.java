package com.java.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.services.CustDim2Service;
import com.java.services.CustDim2ServiceImpl;
import com.java.entity.Status;
import com.java.entity.CustDim11;
import com.java.entity.CustDim2;
import com.java.pojo.Filter;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class CustDim2ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	CustDim2Service service = new CustDim2ServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String application_no ="";
	
    public CustDim2ManagementServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			application_no = (String) session.getAttribute("application_no");

			String func = request.getParameter("func");
			if (func.equals("get")) {
				get(request, response);

			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			application_no = (String) session.getAttribute("application_no");

			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			String func = request.getParameter("func");
			if (func.equals("add")) {
				add(request, response);
			} else if (func.equals("edit")) {
				edit(request, response);
			} else if (func.equals("delete")) {
				delete(request, response);
			} else if (func.equals("export_data")) {
				exportData(request, response);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
			//response.sendRedirect("login.jsp");
		}

	}

	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strt = request.getParameter("start");
		String lim = request.getParameter("limit");
		
		int start_index = Integer.parseInt(strt);
		int limit = Integer.parseInt(lim);
	
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		ArrayList<CustDim2> custdims= null;
		custdims =(ArrayList<CustDim2>) service.getAll(application_no,start_index,limit,filters);		
		int total_results = service.totalCount(application_no,filters);
		
		if(custdims !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        PagedCustDim2 paged_dim = new PagedCustDim2();
	        paged_dim.setTotal_count(total_results);
	        paged_dim.setData(custdims);
	        objectMapper.writeValue(response.getOutputStream(), paged_dim);
		  
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<CustDim2> custdim = Arrays.asList(objectMapper.readValue(request.getInputStream(), CustDim2[].class));
		
		//Get logged in user from session
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
	
		//Validation 
		for(int i=0;i<custdim.size();i++){
			CustDim2 new_custdim = custdim.get(i);
		//reset status
			new_custdim.setColumn_status("");
			new_custdim.setCreated_date(new Date());
			new_custdim.setCreated_user(logged_user);
			new_custdim.setUpdated_date(new Date());
			new_custdim.setApplication_no(application_no);
		Status status =service.addNewCustDim(new_custdim);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<CustDim2> custdims = Arrays.asList(objectMapper.readValue(request.getInputStream(), CustDim2[].class));
	
		for(int i=0;i<custdims.size();i++){
		//Delete will also be sent as a update request
		
			CustDim2 editted_custdim = custdims.get(i);
			
		if(editted_custdim.getColumn_status().equals("D")){
			//Reset the status
			editted_custdim.setColumn_status("");
			
			service.remove(editted_custdim);
		}else if(editted_custdim.getColumn_status().equals("M")){
			editted_custdim.setColumn_status("");
			editted_custdim.setUpdated_date(new Date());
			service.editCustDim(editted_custdim);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<CustDim2> custdims = Arrays.asList(objectMapper.readValue(request.getInputStream(), CustDim2[].class));
	
		//Validation 
		for(int i=0;i<custdims.size();i++){
		service.remove(custdims.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	private void exportData(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		String title =request.getParameter("menu_title");
		//header
	    String menu_name = "Custom Dimension 2";
	    if(title!=null){
	    	menu_name=title;
	    }
		ArrayList<CustDim2> custdims= null;
		custdims =(ArrayList<CustDim2>) service.getAll(application_no,0,-1,filters);	
		
		try {
			    XSSFWorkbook workbook = new XSSFWorkbook();
			    
			    XSSFSheet excelSheet = workbook.createSheet(menu_name+" Management");  


		        XSSFRow row_code = excelSheet.createRow(0);
		        row_code.createCell(0).setCellValue("custdim2_code");
		        row_code.createCell(1).setCellValue("custdim2_description");
		        row_code.createCell(2).setCellValue("custdim2_attribute1");
		        row_code.createCell(3).setCellValue("custdim2_attribute2");
		        row_code.createCell(4).setCellValue("custdim2_attribute3");
		        row_code.createCell(5).setCellValue("custdim2_attribute4");
		        row_code.createCell(6).setCellValue("custdim2_attribute5");
		        CellStyle hiddenstyle = workbook.createCellStyle();
		        hiddenstyle.setHidden(true);	
		        row_code.setRowStyle(hiddenstyle);

		        row_code.setZeroHeight(true);
		       
		        XSSFRow row_header = excelSheet.createRow(1);
		        row_header.createCell(0).setCellValue(menu_name+" Code");
		        row_header.createCell(1).setCellValue(menu_name+" Description");
		        row_header.createCell(2).setCellValue(menu_name+" Attribute 1");
		        row_header.createCell(3).setCellValue(menu_name+" Attribute 2");
		        row_header.createCell(4).setCellValue(menu_name+" Attribute 3");
		        row_header.createCell(5).setCellValue(menu_name+" Attribute 4");
		        row_header.createCell(6).setCellValue(menu_name+" Attribute 5");

			    //Formatting for header
			    CellStyle style = workbook.createCellStyle();
			    Font font = workbook.createFont();
		        font.setColor(IndexedColors.BLUE.getIndex());
		        font.setBold(true);
		        style.setFont(font);
			    
		        row_header.getCell(0).setCellStyle(style); 
		        row_header.getCell(1).setCellStyle(style); 
		        row_header.getCell(2).setCellStyle(style); 
		        row_header.getCell(3).setCellStyle(style); 
		        row_header.getCell(4).setCellStyle(style); 
		        row_header.getCell(5).setCellStyle(style); 
		        row_header.getCell(6).setCellStyle(style); 
		        
		        
			    int j=0;
			    int index=0;
		        for(int i=2;i<custdims.size()+2;i++){   //as index is staring from 2
		        	CustDim2 custdim = custdims.get(index);
		        
		        XSSFRow row = excelSheet.createRow(i);
		        row.createCell(j++).setCellValue(custdim.getCustdim2_code());
		        row.createCell(j++).setCellValue(custdim.getCustdim2_description());
		        row.createCell(j++).setCellValue(custdim.getCustdim2_attribute1());
		        row.createCell(j++).setCellValue(custdim.getCustdim2_attribute2());
		        row.createCell(j++).setCellValue(custdim.getCustdim2_attribute3());
		        row.createCell(j++).setCellValue(custdim.getCustdim2_attribute4());
		        row.createCell(j++).setCellValue(custdim.getCustdim2_attribute5());
		        j=0;
		        index++;
		        }
		        
		        for(int i=0;i<7;i++){
		        	  excelSheet.autoSizeColumn(i);
		        }

			    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		        response.setHeader("Content-Disposition", "attachment; filename="+menu_name+"_management.xlsx");

		        ServletOutputStream out = response.getOutputStream();
		            workbook.write(out);
		            out.flush();
		            out.close();
		       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  
		
	}

	//class created for pagination
		private class PagedCustDim2{
			int total_count;
			List<CustDim2> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<CustDim2> getData() {
				return data;
			}
			public void setData(List<CustDim2> data) {
				this.data = data;
			}
		}

}
		

