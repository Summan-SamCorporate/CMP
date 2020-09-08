package com.java.servlet.etl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.java.entity.Dictionary;
import com.java.services.DictionaryService;
import com.java.services.DictionaryServiceImpl;
import com.java.entity.Status;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class DictionaryManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	DictionaryService service = new DictionaryServiceImpl();
	ObjectMapper objectMapper = new ObjectMapper();
	Status status = Status.SUCCESS;
	String logged_user;
	
    public DictionaryManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	
		ArrayList<Dictionary> dictionaries= null;
		dictionaries =(ArrayList<Dictionary>) service.getAll(start_index,limit,filters);		
		
		int total_results = service.totalCount(filters);
		
		
		if(dictionaries !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	     
	        PagedDictionarys paged_dictionaries = new PagedDictionarys();
	        paged_dictionaries.setTotal_count(total_results);
	        paged_dictionaries.setData(dictionaries);
	        objectMapper.writeValue(response.getOutputStream(), paged_dictionaries);
		  
		}
	    
	}
	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dictionary new_dictionary = objectMapper.readValue(request.getInputStream(), Dictionary.class);
		//reset status
		new_dictionary.setColumn_status("");
		Status status =service.addNewDictionary(new_dictionary);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		List<Dictionary> dictionaries = Arrays.asList(objectMapper.readValue(request.getInputStream(), Dictionary[].class));
		
		for(int i=0;i<dictionaries.size();i++){
			
			Dictionary dictionary = dictionaries.get(i);
		if(dictionary.getColumn_status().equals("D")){
			//Reset the status
			dictionary.setColumn_status("");
			service.remove(dictionary);
		}else if(dictionary.getColumn_status().equals("M")){
			dictionary.setColumn_status("");
		service.editDictionary(dictionary);}
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			List<Dictionary> dictionaries = Arrays.asList(objectMapper.readValue(request.getInputStream(), Dictionary[].class));

			for (int i = 0; i < dictionaries.size(); i++) {
				Dictionary dictionary = dictionaries.get(i);
				
				service.remove(dictionary);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	//class created for pagination
			private class PagedDictionarys{
				int total_count;
				List<Dictionary> data;
				public int getTotal_count() {
					return total_count;
				}
				public void setTotal_count(int total_count) {
					this.total_count = total_count;
				}
				public List<Dictionary> getData() {
					return data;
				}
				public void setData(List<Dictionary> data) {
					this.data = data;
				}
			}

}
		


