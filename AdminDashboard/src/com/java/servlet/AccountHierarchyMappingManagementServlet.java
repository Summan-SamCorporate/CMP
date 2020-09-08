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
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.AccountHierarchyMapping;
import com.java.entity.AccountHierarchyStructure;
import com.java.pojo.Filter;
import com.java.services.AccountHierarchyService;
import com.java.services.AccountHierarchyServiceImpl;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class AccountHierarchyMappingManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	AccountHierarchyService service = new AccountHierarchyServiceImpl();
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String application_no ="";
	
    public AccountHierarchyMappingManagementServlet() {
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
			//List all the account mappings for a selected node
			else if(func.equals("list_mappings")){
				getlist_node_code(request,response);
			}
			//Get all the nodes for the given hierarchy code
			else if (func.equals("get_nodes_list")){
				getlist_hierarchy_code(request , response);
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
			}/* else if (func.equals("export_data")) {
				exportData(request, response);
			}*/
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strt = request.getParameter("start");
		String lim = request.getParameter("limit");
		String account_hierarchy_code = request.getParameter("account_hierarchy_code");
		
		int start_index = Integer.parseInt(strt);
		int limit = Integer.parseInt(lim);
		
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}

		ArrayList<AccountHierarchyMapping> mappings= null;
		mappings =(ArrayList<AccountHierarchyMapping>) service.getAllMapping(application_no,account_hierarchy_code,start_index,limit,filters);		
		
		int total_results = service.totalCount(application_no,filters);
		
		if(mappings !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        PagedMappings paged_categories = new PagedMappings();
	        paged_categories.setTotal_count(total_results);
	        paged_categories.setData(mappings);
	        objectMapper.writeValue(response.getOutputStream(), paged_categories);
		
		}
	    
	}
	
	private void getlist_node_code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String node_code = request.getParameter("node_code");
		
		if(node_code != null){
		ArrayList<AccountHierarchyMapping> mappings= (ArrayList<AccountHierarchyMapping>) service.getAllMapping(node_code);		
		
		
		if(mappings !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        objectMapper.writeValue(response.getOutputStream(), mappings);
		
		}
		}
	    
	}
	private void getlist_hierarchy_code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String account_hierarchy_code = request.getParameter("account_hierarchy_code");
		
		if(account_hierarchy_code != null){
		ArrayList<AccountHierarchyStructure> mappings= (ArrayList<AccountHierarchyStructure>) service.getNodesList(account_hierarchy_code);		
		
		
		if(mappings !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        objectMapper.writeValue(response.getOutputStream(), mappings);
		
		}
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String account_hierarchy_code = request.getParameter("account_hierarchy_code");
		if(account_hierarchy_code != null){
		List<AccountHierarchyMapping> mappings = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchyMapping[].class));
		
		//Get logged in user from session
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
	
		//Validation 
		for(int i=0;i<mappings.size();i++){
			AccountHierarchyMapping new_mapping = mappings.get(i);
		//reset status
			new_mapping.setColumn_status("");
			new_mapping.setCreated_date(new Date());
			new_mapping.setCreated_user(logged_user);
			new_mapping.setUpdated_date(new Date());
			new_mapping.setApplication_no(application_no);
			new_mapping.setAccount_hierarchy_code(account_hierarchy_code);
		Status status =service.addNewMapping(new_mapping);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<AccountHierarchyMapping> mappings = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchyMapping[].class));
	
		for(int i=0;i<mappings.size();i++){
		//Delete will also be sent as a update request
		
			AccountHierarchyMapping editted_mappings = mappings.get(i);
			
		if(editted_mappings.getColumn_status().equals("D")){
			//Reset the status
			editted_mappings.setColumn_status("");
			
			service.removeMapping(editted_mappings);
		}
		else if(editted_mappings.getColumn_status().equals("M")){
			editted_mappings.setColumn_status("");
			editted_mappings.setUpdated_date(new Date());
			service.editMapping(editted_mappings);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<AccountHierarchyMapping> mappings = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchyMapping[].class));
	
		//Validation 
		for(int i=0;i<mappings.size();i++){
		service.removeMapping(mappings.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
/*	private void exportData(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		
		String title =request.getParameter("menu_title");
		//header
	    String menu_name = "Category";
	    if(title!=null){
	    	menu_name=title;
	    }
		ArrayList<Category> categories= null;
		categories =(ArrayList<Category>) service.getAll(application_no,0,-1,filters);	
		
		try {
			    XSSFWorkbook workbook = new XSSFWorkbook();
			    
			    XSSFSheet excelSheet = workbook.createSheet(menu_name+" Management");  


		        XSSFRow row_code = excelSheet.createRow(0);
		        row_code.createCell(0).setCellValue("category_code");
		        row_code.createCell(1).setCellValue("category_description");
		        row_code.createCell(2).setCellValue("category_type");
		        row_code.createCell(3).setCellValue("cat_Attribute1");
		        row_code.createCell(4).setCellValue("cat_Attribute2");
		        row_code.createCell(5).setCellValue("cat_Attribute3");
		        row_code.createCell(6).setCellValue("cat_Attribute4");
		        row_code.createCell(7).setCellValue("cat_Attribute5");
		        CellStyle hiddenstyle = workbook.createCellStyle();
		        hiddenstyle.setHidden(true);	
		        row_code.setRowStyle(hiddenstyle);

		        row_code.setZeroHeight(true);
		       
		        XSSFRow row_header = excelSheet.createRow(1);
		        row_header.createCell(0).setCellValue(menu_name+" Code");
		        row_header.createCell(1).setCellValue(menu_name+" Description");
		        row_header.createCell(2).setCellValue("Category Type");
		        row_header.createCell(3).setCellValue(menu_name+" Attribute 1");
		        row_header.createCell(4).setCellValue(menu_name+" Attribute 2");
		        row_header.createCell(5).setCellValue(menu_name+" Attribute 3");
		        row_header.createCell(6).setCellValue(menu_name+" Attribute 4");
		        row_header.createCell(7).setCellValue(menu_name+" Attribute 5");

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
		        row_header.getCell(7).setCellStyle(style); 
		        
		        
			    int j=0;
			    int index=0;
		        for(int i=2;i<categories.size()+2;i++){   //as index is staring from 2
		        Category category = categories.get(index);
		        
		        XSSFRow row = excelSheet.createRow(i);
		        row.createCell(j++).setCellValue(category.getCategory_code());
		        row.createCell(j++).setCellValue(category.getCategory_description());
		      //  row.createCell(j++).setCellValue(category.getCategory_type().getDescription());
		        row.createCell(j++).setCellValue(category.getCat_attribute1());
		        row.createCell(j++).setCellValue(category.getCat_attribute2());
		        row.createCell(j++).setCellValue(category.getCat_attribute3());
		        row.createCell(j++).setCellValue(category.getCat_attribute4());
		        row.createCell(j++).setCellValue(category.getCat_attribute5());
		        j=0;
		        index++;
		        }
		        
		        for(int i=0;i<15;i++){
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
	  
		
	}*/


	//class created for pagination
		private class PagedMappings{
			int total_count;
			List<AccountHierarchyMapping> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<AccountHierarchyMapping> getData() {
				return data;
			}
			public void setData(List<AccountHierarchyMapping> data) {
				this.data = data;
			}
		}
}
		

