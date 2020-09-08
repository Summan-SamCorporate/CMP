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
import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Roles;
import com.java.services.RolesService;
import com.java.services.RolesServiceImpl;
import com.java.services.UsersService;
import com.java.services.UsersServiceImpl;
import com.java.entity.Status;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	UsersService service = new UsersServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	
    public UserManagementServlet() {
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
			// Get logged in user from session
			logged_user = (String) request.getSession(false).getAttribute("user_name");

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
		
		ArrayList<Users> users= null;
		users =(ArrayList<Users>) service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		
		if(users !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      	        
	        PagedUsers paged_users = new PagedUsers();
	        paged_users.setTotal_count(total_results);
	        paged_users.setData(users);
	        objectMapper.writeValue(response.getOutputStream(), paged_users);
		  
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Users> new_users = Arrays.asList(objectMapper.readValue(request.getInputStream(), Users[].class));
		
		//Validation 
		for(int i=0;i<new_users.size();i++){
		Users new_user = new_users.get(i);
		//reset status
		new_user.setColumn_status("");
		new_user.setCreated_date(new Date());
		new_user.setCreated_user(logged_user);
		new_user.setCod_pwd_rule("*");
		new_user.setSession_time_out(60000);
		new_user.setStylesheet_name("./resources/theme-classic/CMP-all.css");
		Status status =service.addNewUser(new_user);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Users> new_users = Arrays.asList(objectMapper.readValue(request.getInputStream(), Users[].class));
		for(int i=0;i<new_users.size();i++){
		//Delete will also be sent as a update request
		
			Users new_user = new_users.get(i);
			
		if(new_user.getColumn_status().equals("D")){
			//Reset the status
			new_user.setColumn_status("");
			
			service.remove(new_user);
		}else if(new_user.getColumn_status().equals("M")){
			new_user.setColumn_status("");
			new_user.setUpdated_date(new Date());
			new_user.setUpdated_user(logged_user);
			service.editUser(new_user);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Users> users = Arrays.asList(objectMapper.readValue(request.getInputStream(), Users[].class));
	
		//Validation 
		for(int i=0;i<users.size();i++){
		service.remove(users.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
	//class created for pagination
		private class PagedUsers{
			int total_count;
			List<Users> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<Users> getData() {
				return data;
			}
			public void setData(List<Users> data) {
				this.data = data;
			}
		}
}
		


