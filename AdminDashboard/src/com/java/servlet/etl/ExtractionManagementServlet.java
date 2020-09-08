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
import com.java.services.ExtractionService;
import com.java.services.ExtractionServiceImpl;
import com.java.entity.Status;
import com.java.entity.Extraction;
import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class ExtractionManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ExtractionService service = new ExtractionServiceImpl();
	ObjectMapper objectMapper = new ObjectMapper();

	Status status = Status.SUCCESS;
	String logged_user;
	String current_container="";
	
    public ExtractionManagementServlet() {
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

		ArrayList<Extraction> extractions= null;
		extractions =(ArrayList<Extraction>)  service.getAll(logged_user,start_index,limit,filters);		
		
		int total_results = service.totalCount(logged_user,filters);
		
		
		if(extractions !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        objectMapper.writeValue(response.getOutputStream(), extractions);
		  	}
	    
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		ArrayList<Extraction> extractions= null;
		extractions =(ArrayList<Extraction>)  service.getAll(logged_user);		
		
		//int total_results = service.totalCount(logged_user);
		
		
		if(extractions !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        objectMapper.writeValue(response.getOutputStream(), extractions);
		  	}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Extraction> extractions = Arrays.asList(objectMapper.readValue(request.getInputStream(), Extraction[].class));

		//Validation 
		for(int i=0;i<extractions.size();i++){
			Extraction new_extraction = extractions.get(i);
		//reset status
			new_extraction.setColumn_status("");
			new_extraction.setCreated_date(new Date());
			new_extraction.setCreated_user(logged_user);
			new_extraction.setUpdated_date(new Date());
			new_extraction.setContainer_code(current_container);
			Status status =service.addNewExtraction(new_extraction);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Extraction> containers = Arrays.asList(objectMapper.readValue(request.getInputStream(), Extraction[].class));
		for(int i=0;i<containers.size();i++){
		//Delete will also be sent as a update request
		
			Extraction container = containers.get(i);
			
		if(container.getColumn_status().equals("D")){
			//Reset the status
			container.setColumn_status("");
			
			service.remove(container);
		}else if(container.getColumn_status().equals("M")){
			container.setColumn_status("");
			container.setUpdated_date(new Date());
			service.editExtraction(container);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Extraction> extractions = Arrays.asList(objectMapper.readValue(request.getInputStream(), Extraction[].class));
	
		//Validation 
		for(int i=0;i< extractions.size();i++){
		service.remove( extractions.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	

	
}
		


