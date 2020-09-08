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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.services.PeriodService;
import com.java.services.PeriodServiceImpl;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.pojo.Filter;
import com.java.entity.Period;

/**
 * Servlet implementation class InputRawBas
 */
public class PeriodManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	PeriodService service = new PeriodServiceImpl();
	//Object Mapper to map JSON object to Period class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no ="";
	
    public PeriodManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
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

			// Get logged in user from session
			logged_user = (String) session.getAttribute("user_name");

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

		ArrayList<Period> periods= null;
		periods =(ArrayList<Period>) service.getAll(application_no,start_index,limit,filters);		
		
		int total_results = service.totalCount(application_no,filters);
		
		if(periods !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        PagedPeriods paged_periods = new PagedPeriods();
	        paged_periods.setTotal_count(total_results);
	        paged_periods.setData(periods);
	        objectMapper.writeValue(response.getOutputStream(), paged_periods);
		        
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Period> periods = Arrays.asList(objectMapper.readValue(request.getInputStream(), Period[].class));
		
		//Validation 
		for (int i = 0; i < periods.size(); i++) {
			Period new_period = periods.get(i);
			// reset status
			new_period.setColumn_status("");
			new_period.setCreated_date(new Date());
			new_period.setCreated_user(logged_user);
			new_period.setUpdated_date(new Date());
			new_period.setApplication_no(application_no);
			Status status = service.addNewPeriod(new_period);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Period> periods = Arrays.asList(objectMapper.readValue(request.getInputStream(), Period[].class));
		for(int i=0;i<periods.size();i++){
		//Delete will also be sent as a update request
		
			Period period = periods.get(i);
			
		if(period.getColumn_status().equals("D")){
			//Reset the status
			period.setColumn_status("");
			
			service.remove(period);
		}else if(period.getColumn_status().equals("M")){
			period.setColumn_status("");
			period.setUpdated_date(new Date());
			service.editPeriod(period);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Period> periods = Arrays.asList(objectMapper.readValue(request.getInputStream(), Period[].class));
	
		//Validation 
		for(int i=0;i<periods.size();i++){
		service.remove(periods.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	//class created for pagination
	private class PagedPeriods{
		int total_count;
		List<Period> data;
		public int getTotal_count() {
			return total_count;
		}
		public void setTotal_count(int total_count) {
			this.total_count = total_count;
		}
		public List<Period> getData() {
			return data;
		}
		public void setData(List<Period> data) {
			this.data = data;
		}
	}
	
	private void exportData(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {

		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		
		//header
		ArrayList<Period> periods= null;
		periods =(ArrayList<Period>) service.getAll(application_no,0,-1,filters);	
		
		try {
			    XSSFWorkbook workbook = new XSSFWorkbook();
			    
			    XSSFSheet excelSheet = workbook.createSheet("Period Management");  


		        XSSFRow row_code = excelSheet.createRow(0);
		        row_code.createCell(0).setCellValue("period_code");
		        row_code.createCell(1).setCellValue("period_name");
		        row_code.createCell(2).setCellValue("month_name");
		        row_code.createCell(3).setCellValue("quarter_name");
		        row_code.createCell(4).setCellValue("four_month_name");
		        row_code.createCell(5).setCellValue("semester_name");
		        CellStyle hiddenstyle = workbook.createCellStyle();
		        hiddenstyle.setHidden(true);	
		        row_code.setRowStyle(hiddenstyle);

		        row_code.setZeroHeight(true);
		       
		        XSSFRow row_header = excelSheet.createRow(1);
		        row_header.createCell(0).setCellValue("Period Code");
		        row_header.createCell(1).setCellValue("Period Name");
		        row_header.createCell(2).setCellValue("Month Name");
		        row_header.createCell(3).setCellValue("Quater Name");
		        row_header.createCell(4).setCellValue("Four Month Name");
		        row_header.createCell(5).setCellValue("Semester Name");
		       
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
		        
			    int j=0;
			    int index=0;
		        for(int i=2;i<periods.size()+2;i++){   //as index is staring from 2
		        Period period = periods.get(index);
		        
		        XSSFRow row = excelSheet.createRow(i);
		        row.createCell(j++).setCellValue(period.getPeriod_code());
		        row.createCell(j++).setCellValue(period.getPeriod_name());
		        row.createCell(j++).setCellValue(period.getMonth_name());
		        row.createCell(j++).setCellValue(period.getQuarter_name());
		        row.createCell(j++).setCellValue(period.getFour_month_name());
		        row.createCell(j++).setCellValue(period.getSemester_name());
		        j=0;
		        index++;
		        }
		        
		        for(int i=0;i<15;i++){
		        	  excelSheet.autoSizeColumn(i);
		        }

			    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		        response.setHeader("Content-Disposition", "attachment; filename="+"period_management.xlsx");

		        ServletOutputStream out = response.getOutputStream();
		            workbook.write(out);
		            out.flush();
		            out.close();
		       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  
		
	}


	
}
		


