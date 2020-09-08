package com.java.servlet;
/* Created By: Summan Bahadur
 * Description: Servlet for Account Hierarchy Management 
 * Reference/s: Url /AccountHierarchyManagement
 * 
 * */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.java.services.AccountHierarchyService;
import com.java.services.AccountHierarchyServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.entity.AccountHierarchy;
import com.java.entity.Status;
import com.java.pojo.Filter;

/**
 * Servlet implementation class InputRawBas
 */
public class AccountHierarchyManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service variable

	AccountHierarchyService service = new AccountHierarchyServiceImpl();
	
	// Object Mapper to map JSON object
	ObjectMapper objectMapper = new ObjectMapper();

	// Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no = "";

	public AccountHierarchyManagementServlet() {

		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			application_no = (String) session.getAttribute("application_no");
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
			application_no = (String) session.getAttribute("application_no");
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			logged_user = (String) session.getAttribute("user_name");

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

	private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String strt = request.getParameter("start");
		String lim = request.getParameter("limit");


		int start_index = Integer.parseInt(strt);
		int limit = Integer.parseInt(lim);

		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}

		ArrayList<AccountHierarchy> hierarchy = null;
			hierarchy = (ArrayList<AccountHierarchy>) service.getAll(application_no,start_index,limit,filters);

			int total_results = service.totalCount(logged_user,filters);
			
			
			if(hierarchy !=null){ 
		        response.setContentType("application/json");            
		        response.setCharacterEncoding("UTF-8");
		      
		        PagedAccounts paged_applications = new PagedAccounts();
		        paged_applications.setTotal_count(total_results);
		        paged_applications.setData(hierarchy);
		        objectMapper.writeValue(response.getOutputStream(), paged_applications);
			  	}

	}
	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<AccountHierarchy> hierarchys = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchy[].class));

		// Validation
		for (int i = 0; i < hierarchys.size(); i++) {
			AccountHierarchy new_hierarchy = hierarchys.get(i);
			// reset status
			new_hierarchy.setColumn_status("");
			new_hierarchy.setCreated_date(new Date());
			new_hierarchy.setCreated_user(logged_user);
			new_hierarchy.setUpdated_date(new Date());
			new_hierarchy.setApplication_no(application_no);
			Status status = service.addNewHierarchy(new_hierarchy);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<AccountHierarchy> hierarchys = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchy[].class));

		for (int i = 0; i < hierarchys.size(); i++) {
			// Delete will also be sent as a update request
			AccountHierarchy hierarchy = hierarchys.get(i);

			if (hierarchy.getColumn_status().equals("D")) {
				// Reset the status
				hierarchy.setColumn_status("");

				service.remove(hierarchy);
			} else if (hierarchy.getColumn_status().equals("M")) {
				hierarchy.setColumn_status("");
				hierarchy.setUpdated_date(new Date());
				service.editHierarchy(hierarchy);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			List<AccountHierarchy> hierarchys = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchy[].class));

			// Validation
			for (int i = 0; i < hierarchys.size(); i++) {
				service.remove(hierarchys.get(i));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}


	// class created for pagination
	private class PagedAccounts {
		int total_count;
		List<AccountHierarchy> data;

		public int getTotal_count() {
			return total_count;
		}

		public void setTotal_count(int total_count) {
			this.total_count = total_count;
		}

		public List<AccountHierarchy> getData() {
			return data;
		}

		public void setData(List<AccountHierarchy> data) {
			this.data = data;
		}
	}
}
