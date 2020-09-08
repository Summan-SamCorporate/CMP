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
import com.java.services.LabelService;
import com.java.services.LabelServiceImpl;
import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Label;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class LabelManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	LabelService service = new LabelServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	
    public LabelManagementServlet() {
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

		ArrayList<Label> label= null;
		label =(ArrayList<Label>)  service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		
		if(label !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        PagedLabel paged_label = new PagedLabel();
	        paged_label.setTotal_count(total_results);
	        paged_label.setData(label);
	        objectMapper.writeValue(response.getOutputStream(), paged_label);
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Label> label = Arrays.asList(objectMapper.readValue(request.getInputStream(), Label[].class));
		
		//Get logged in user from session
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
	
		//Validation 
		for(int i=0;i<label.size();i++){
			Label new_label = label.get(i);
		//reset status
			new_label.setColumn_status("");
			new_label.setCreated_date(new Date());
			new_label.setCreated_user(logged_user);
			new_label.setUpdated_date(new Date());
		Status status =service.addNewLabel(new_label);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Label> label = Arrays.asList(objectMapper.readValue(request.getInputStream(), Label[].class));
	
		for(int i=0;i<label.size();i++){
		//Delete will also be sent as a update request
		
			Label editted_label = label.get(i);
			
		if(editted_label.getColumn_status().equals("D")){
			//Reset the status
			editted_label.setColumn_status("");
			
			service.remove(editted_label);
		}else if(editted_label.getColumn_status().equals("M")){
			editted_label.setColumn_status("");
			editted_label.setUpdated_date(new Date());
			service.editLabel(editted_label);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Label> label = Arrays.asList(objectMapper.readValue(request.getInputStream(), Label[].class));
	
		//Validation 
		for(int i=0;i<label.size();i++){
		service.remove(label.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}

	//class created for pagination
		private class PagedLabel{
			int total_count;
			List<Label> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<Label> getData() {
				return data;
			}
			public void setData(List<Label> data) {
				this.data = data;
			}
		}
	
}
		

