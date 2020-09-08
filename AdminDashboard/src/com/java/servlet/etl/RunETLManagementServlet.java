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
import com.java.services.ContainerService;
import com.java.services.ContainerServiceImpl;
import com.java.entity.Status;
import com.java.entity.Container;
import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class RunETLManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ContainerService service = new ContainerServiceImpl();
	ObjectMapper objectMapper = new ObjectMapper();

	Status status = Status.SUCCESS;
	String logged_user;
	
    public RunETLManagementServlet() {
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
			 else if(func.equals("chng_appln")){ //Function to change application number in session
				 changeContainerNo(request,response);
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

		ArrayList<Container> containers= null;
		containers =(ArrayList<Container>)  service.getAll(logged_user,start_index,limit,filters);		
		
		int total_results = service.totalCount(logged_user,filters);
		
		
		if(containers !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	       /* PagedApplication paged_applications = new PagedApplication();
	        paged_applications.setTotal_count(total_results);
	        paged_applications.setData(containers);
	       */ objectMapper.writeValue(response.getOutputStream(), containers);
		  	}
	    
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		ArrayList<Container> containers= null;
		containers =(ArrayList<Container>)  service.getAll(logged_user);		
		
		//int total_results = service.totalCount(logged_user);
		
		
		if(containers !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        objectMapper.writeValue(response.getOutputStream(), containers);
		  	}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Container> containers = Arrays.asList(objectMapper.readValue(request.getInputStream(), Container[].class));
		
		//Validation 
		for(int i=0;i<containers.size();i++){
			Container new_container = containers.get(i);
		//reset status
			new_container.setColumn_status("");
			new_container.setCreated_date(new Date());
			new_container.setCreated_user(logged_user);
			new_container.setUpdated_date(new Date());
			Status status =service.addNewContainer(new_container);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Container> containers = Arrays.asList(objectMapper.readValue(request.getInputStream(), Container[].class));
		for(int i=0;i<containers.size();i++){
		//Delete will also be sent as a update request
		
			Container container = containers.get(i);
			
		if(container.getColumn_status().equals("D")){
			//Reset the status
			container.setColumn_status("");
			
			service.remove(container);
		}else if(container.getColumn_status().equals("M")){
			container.setColumn_status("");
			container.setUpdated_date(new Date());
			service.editContainer(container);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Container> containers = Arrays.asList(objectMapper.readValue(request.getInputStream(), Container[].class));
	
		//Validation 
		for(int i=0;i< containers.size();i++){
		service.remove( containers.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	
	
	//Change Application Number in Session
	private void changeContainerNo(HttpServletRequest request, HttpServletResponse response) {
	
		String container_code =request.getParameter("container_code");
		String description =request.getParameter("description");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("container_no", container_code);
		session.setAttribute("container_name", description);
		
	}
	//class created for pagination
/*	private class PagedApplication {
		int total_count;
		List<Application> data;

		public int getTotal_count() {
			return total_count;
		}

		public void setTotal_count(int total_count) {
			this.total_count = total_count;
		}

		public List<Application> getData() {
			return data;
		}

		public void setData(List<Application> data) {
			this.data = data;
		}
	}*/
	
}
		


