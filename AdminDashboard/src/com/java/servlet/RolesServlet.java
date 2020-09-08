package com.java.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.entity.Category;
import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Roles;
import com.java.services.RolesService;
import com.java.services.RolesServiceImpl;
import com.java.entity.Status;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class RolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	RolesService service = new RolesServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	Status status = Status.SUCCESS;
	
	
    public RolesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		HttpSession session = request.getSession(false);
		 if (session != null)
		 {	
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		String func = request.getParameter("func");
		if (func.equals("get")) {
			get(request, response);

		}
		else if (func.equals("getAll")) {
			getAll(request, response);

		}
		
		}else{
			response.sendRedirect("login.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			String func = request.getParameter("func");
			
			if (func.equals("add")) {
				add(request, response);
			}
			else if (func.equals("edit")) {
				edit(request, response);
			}
		
		}else{
			response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
			//response.sendRedirect("login.jsp");
		}
	}

	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		ArrayList<Roles> roles = (ArrayList<Roles>) service.get();		
		if(roles !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        objectMapper.writeValue(response.getOutputStream(), roles);
		}
		else{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String strt = request.getParameter("start");
		String lim = request.getParameter("limit");
		
		int start_index = Integer.parseInt(strt);
		int limit = Integer.parseInt(lim);
		
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		
		ArrayList<Roles> roles = (ArrayList<Roles>) service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		if(roles !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	        PagedRoles paged_roles = new PagedRoles();
	        paged_roles.setTotal_count(total_results);
	        paged_roles.setData(roles);
	        objectMapper.writeValue(response.getOutputStream(), paged_roles);
		}
		else{
			 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	    
	}

	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Roles> roles = Arrays.asList(objectMapper.readValue(request.getInputStream(), Roles[].class));
		
		String logged_user =(String) request.getSession(false).getAttribute("user_name");
		
		for(int i=0;i<roles.size();i++){
			Roles new_role = roles.get(i);
			new_role.setColumn_status("");
			new_role.setCreated_date(new Date());
			new_role.setCreated_user(logged_user);
			new_role.setUpdated_date(new Date());

			Status status =service.addNewRole(new_role);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}		 
			if(status.getCode() == 1){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
		}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Roles> roles = Arrays.asList(objectMapper.readValue(request.getInputStream(), Roles[].class));
		
		for(int i=0;i<roles.size();i++){
		//Delete will also be sent as a update request
		
		Roles editted_role = roles.get(i);
			
		Status status = Status.SUCCESS;
		
		if(editted_role.getColumn_status().equals("D")){
			editted_role.setColumn_status("");
			status = service.remove(editted_role);
	        
		}else if(editted_role.getColumn_status().equals("M")){
			editted_role.setColumn_status("");
			editted_role.setUpdated_date(new Date());
			status = service.editRole(editted_role);
		}
		
		if(status.getCode()==-7){
	        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	        }
		else if(status.getCode() == 1){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	   }
	}

	//class created for pagination
			private class PagedRoles{
				int total_count;
				List<Roles> data;
				public int getTotal_count() {
					return total_count;
				}
				public void setTotal_count(int total_count) {
					this.total_count = total_count;
				}
				public List<Roles> getData() {
					return data;
				}
				public void setData(List<Roles> data) {
					this.data = data;
				}
			}
}
		


