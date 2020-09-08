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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.json.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.services.CategoryTypeService;
import com.java.services.CategoryTypeServiceImpl;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.CategoryType;
import com.java.pojo.Filter;

/**
 * Servlet implementation class InputRawBas
 */
public class CategoryTypeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	CategoryTypeService service = new CategoryTypeServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	String application_no ="";
	
	//Status to return
	Status status = Status.SUCCESS;
	
    public CategoryTypeManagementServlet() {
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
			
			String func = request.getParameter("func");
			if (func.equals("add")) {
				add(request, response);
			} else if (func.equals("edit")) {
				edit(request, response);
			} else if (func.equals("delete")) {
				delete(request, response);
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

		ArrayList<CategoryType> category_types= null;
		category_types =(ArrayList<CategoryType>) service.getAll(application_no,start_index,limit,filters);		
		
		int total_results = service.totalCount(application_no,filters);
		
		if(category_types !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        PagedCategoryTypes paged_categorytypes = new PagedCategoryTypes();
	        paged_categorytypes.setTotal_count(total_results);
	        paged_categorytypes.setData(category_types);
	        objectMapper.writeValue(response.getOutputStream(), paged_categorytypes);
		   
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<CategoryType> category_type = Arrays.asList(objectMapper.readValue(request.getInputStream(), CategoryType[].class));
		
		//Get logged in user from session
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
	
		//Validation 
		for(int i=0;i<category_type.size();i++){
			CategoryType new_category = category_type.get(i);
		//reset status
			new_category.setColumn_status("");
			new_category.setCreated_date(new Date());
			new_category.setCreated_user(logged_user);
			new_category.setUpdated_date(new Date());
			new_category.setApplication_no(application_no);
		Status status =service.addNewCategory(new_category);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<CategoryType> category_type = Arrays.asList(objectMapper.readValue(request.getInputStream(), CategoryType[].class));
	
		for(int i=0;i<category_type.size();i++){
		//Delete will also be sent as a update request
		
			CategoryType editted_category = category_type.get(i);
			
		if(editted_category.getColumn_status().equals("D")){
			//Reset the status
			editted_category.setColumn_status("");
			
			service.remove(editted_category);
		}else if(editted_category.getColumn_status().equals("M")){
			editted_category.setColumn_status("");
			editted_category.setUpdated_date(new Date());
			service.editCategory(editted_category);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<CategoryType> category_type = Arrays.asList(objectMapper.readValue(request.getInputStream(), CategoryType[].class));
	
		//Validation 
		for(int i=0;i<category_type.size();i++){
		service.remove(category_type.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	

	//class created for pagination
		private class PagedCategoryTypes{
			int total_count;
			List<CategoryType> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<CategoryType> getData() {
				return data;
			}
			public void setData(List<CategoryType> data) {
				this.data = data;
			}
		}
}
		

