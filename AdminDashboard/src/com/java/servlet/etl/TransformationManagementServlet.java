package com.java.servlet.etl;

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
import com.java.services.TransformationService;
import com.java.services.TransformationServiceImpl;
import com.java.entity.Status;
import com.java.entity.Transformation;
import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class TransformationManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TransformationService service = new TransformationServiceImpl();
	ObjectMapper objectMapper = new ObjectMapper();

	Status status = Status.SUCCESS;
	String logged_user;
	String current_container;
	
    public TransformationManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			logged_user = (String) request.getSession(false).getAttribute("user_name");
			String func = request.getParameter("func");
			if (func.equals("get")) {
				get(request, response);
			} else if (func.equals("getAll")) {
				getAll(request, response);
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		 if (session != null)
		 {
			 objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
			 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 
			 logged_user =(String) request.getSession(false).getAttribute("user_name");
			 current_container  = (String) session.getAttribute("container_no");

			 String func = request.getParameter("func");
			 if (func.equals("add")) {
				 add(request, response);
			 }
			 else if (func.equals("edit")) {
				 edit(request, response);
			 }
			 else if (func.equals("delete")) {
				 delete(request, response);
			 }
		 }else{
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

		ArrayList<Transformation> transformations= null;
		transformations =(ArrayList<Transformation>)  service.getAll(logged_user,start_index,limit,filters);		
		
		int total_results = service.totalCount(logged_user,filters);
		
		
		if(transformations !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        objectMapper.writeValue(response.getOutputStream(), transformations);
		  	}
	    
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		ArrayList<Transformation> transformations= null;
		transformations =(ArrayList<Transformation>)  service.getAll(logged_user);		
		
		//int total_results = service.totalCount(logged_user);
		
		
		if(transformations !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        objectMapper.writeValue(response.getOutputStream(), transformations);
		  	}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Transformation> transformations = Arrays.asList(objectMapper.readValue(request.getInputStream(), Transformation[].class));
		
		//Validation 
		for(int i=0;i<transformations.size();i++){
			Transformation new_transformation = transformations.get(i);
		//reset status
			new_transformation.setColumn_status("");
			new_transformation.setCreated_date(new Date());
			new_transformation.setCreated_user(logged_user);
			new_transformation.setContainer_code(current_container);
			new_transformation.setUpdated_date(new Date());
			Status status =service.addNewTransformation(new_transformation);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Transformation> transformations = Arrays.asList(objectMapper.readValue(request.getInputStream(), Transformation[].class));
		for(int i=0;i<transformations.size();i++){
		//Delete will also be sent as a update request
		
			Transformation transformation = transformations.get(i);
			
		if(transformation.getColumn_status().equals("D")){
			//Reset the status
			transformation.setColumn_status("");
			
			service.remove(transformation);
		}else if(transformation.getColumn_status().equals("M")){
			transformation.setColumn_status("");
			transformation.setUpdated_date(new Date());
			service.editTransformation(transformation);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Transformation> transformations = Arrays.asList(objectMapper.readValue(request.getInputStream(), Transformation[].class));
	
		//Validation 
		for(int i=0;i< transformations.size();i++){
		service.remove( transformations.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
		
}
		


