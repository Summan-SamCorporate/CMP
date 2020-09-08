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
import com.java.services.UserStatusService;
import com.java.services.UserStatusServiceImpl;
import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.UserStatus;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class UserStatusManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserStatusService service = new UserStatusServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	
    public UserStatusManagementServlet() {
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
		
		ArrayList<UserStatus> user_status= null;
		user_status =(ArrayList<UserStatus>) service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		
		if(user_status !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        PagedUserStatus paged_user_status = new PagedUserStatus();
	        paged_user_status.setTotal_count(total_results);
	        paged_user_status.setData(user_status);
	        objectMapper.writeValue(response.getOutputStream(), paged_user_status);
		
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<UserStatus> status = Arrays.asList(objectMapper.readValue(request.getInputStream(), UserStatus[].class));
		
		//Get logged in user from session
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
	
		//Validation 
		for(int i=0;i<status.size();i++){
			UserStatus new_status = status.get(i);
		//reset status
			new_status.setColumn_status("");
			new_status.setCreated_date(new Date());
			new_status.setCreated_user(logged_user);
			new_status.setUpdated_date(new Date());
		Status s =service.addNewStatus(new_status);
		if(s.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<UserStatus> status = Arrays.asList(objectMapper.readValue(request.getInputStream(), UserStatus[].class));
	
		for(int i=0;i<status.size();i++){
		//Delete will also be sent as a update request
		
			UserStatus editted_status = status.get(i);
			
		if(editted_status.getColumn_status().equals("D")){
			//Reset the status
			editted_status.setColumn_status("");
			
			service.remove(editted_status);
		}else if(editted_status.getColumn_status().equals("M")){
			editted_status.setColumn_status("");
			editted_status.setUpdated_date(new Date());
			service.editStatus(editted_status);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<UserStatus> status = Arrays.asList(objectMapper.readValue(request.getInputStream(), UserStatus[].class));
	
		//Validation 
		for(int i=0;i<status.size();i++){
		service.remove(status.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	

	//class created for pagination
		private class PagedUserStatus{
			int total_count;
			List<UserStatus> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<UserStatus> getData() {
				return data;
			}
			public void setData(List<UserStatus> data) {
				this.data = data;
			}
		}
}
		

