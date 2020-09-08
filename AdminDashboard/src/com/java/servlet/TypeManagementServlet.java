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
import com.java.services.TypeService;
import com.java.services.TypeServiceImpl;
import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.Type;import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class TypeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	TypeService service = new TypeServiceImpl();
	//Object Mapper to map JSON object to Type class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	
    public TypeManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		HttpSession session = request.getSession(false);
		if (session != null) {
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

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		HttpSession session = request.getSession(false);
		if (session != null) {
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
		
		ArrayList<Type> type= null;
		type =(ArrayList<Type>) service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		
		if(type !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	       
	        PagedTypes paged_types = new PagedTypes();
	        paged_types.setTotal_count(total_results);
	        paged_types.setData(type);
	        objectMapper.writeValue(response.getOutputStream(), paged_types);
		 		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Type> types = Arrays.asList(objectMapper.readValue(request.getInputStream(), Type[].class));
		
		//Get logged in user from session
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
	
		//Validation 
		for(int i=0;i<types.size();i++){
			Type new_type = types.get(i);
		//reset status
			new_type.setColumn_status("");
			new_type.setCreated_date(new Date());
			new_type.setCreated_user(logged_user);
			new_type.setUpdated_date(new Date());
		Status status =service.addNewType(new_type);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Type> types = Arrays.asList(objectMapper.readValue(request.getInputStream(), Type[].class));
	
		for(int i=0;i<types.size();i++){
		//Delete will also be sent as a update request
		
			Type editted_type = types.get(i);
			
		if(editted_type.getColumn_status().equals("D")){
			//Reset the status
			editted_type.setColumn_status("");
			
			service.remove(editted_type);
		}else if(editted_type.getColumn_status().equals("M")){
			editted_type.setColumn_status("");
			editted_type.setUpdated_date(new Date());
			service.editType(editted_type);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Type> types = Arrays.asList(objectMapper.readValue(request.getInputStream(), Type[].class));
	
		//Validation 
		for(int i=0;i<types.size();i++){
		service.remove(types.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}

	//class created for pagination
		private class PagedTypes{
			int total_count;
			List<Type> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<Type> getData() {
				return data;
			}
			public void setData(List<Type> data) {
				this.data = data;
			}
		}

	
}
		

