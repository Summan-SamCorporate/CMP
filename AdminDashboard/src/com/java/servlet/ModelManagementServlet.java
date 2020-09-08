package com.java.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.pojo.Filter;
import com.java.entity.ApplicationStatus;
import com.java.entity.Model;
import com.java.services.ModelService;
import com.java.services.ModelServiceImpl;
import com.java.entity.Status;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class ModelManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	ModelService service = new ModelServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	
    public ModelManagementServlet() {
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

		HttpSession session = request.getSession(false);
		if (session != null) {
			logged_user = (String) session.getAttribute("user_name");
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
	
		ArrayList<Model> models= null;
		models =(ArrayList<Model>) service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		
		if(models !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	     
	        PagedModels paged_models = new PagedModels();
	        paged_models.setTotal_count(total_results);
	        paged_models.setData(models);
	        objectMapper.writeValue(response.getOutputStream(), paged_models);
		  
		}
	    
	}
	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Model> new_models = Arrays.asList(objectMapper.readValue(request.getInputStream(), Model[].class));
		//reset status3
		

		for(int i=0;i<new_models.size();i++){
		
			Model new_model = new_models.get(i);
			
			new_model.setColumn_status("");
			new_model.setCreated_date(new Date());
			new_model.setCreated_user(logged_user);
			Status status =service.addNewModel(new_model);
				if(status.getCode()==2){ //NonUniqueKey
					response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			
				}

		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		List<Model> models = Arrays.asList(objectMapper.readValue(request.getInputStream(), Model[].class));
		
		for(int i=0;i<models.size();i++){
			
			Model model = models.get(i);
		if(model.getColumn_status().equals("D")){
			//Reset the status
			model.setColumn_status("");
			service.remove(model);
		}else if(model.getColumn_status().equals("M")){
			model.setColumn_status("");
		service.editModel(model);}
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			List<Model> models = Arrays.asList(objectMapper.readValue(request.getInputStream(), Model[].class));

			for (int i = 0; i < models.size(); i++) {
				Model model = models.get(i);
				
				service.remove(model);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	//class created for pagination
			private class PagedModels{
				int total_count;
				List<Model> data;
				public int getTotal_count() {
					return total_count;
				}
				public void setTotal_count(int total_count) {
					this.total_count = total_count;
				}
				public List<Model> getData() {
					return data;
				}
				public void setData(List<Model> data) {
					this.data = data;
				}
			}

}
		


