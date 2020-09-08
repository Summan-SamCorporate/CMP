package com.java.servlet;

/*
 * Created By: Summan Bahadur
 * Description: Servlet called for Application Management in accordion 
 * Reference/s: applicationmanagement frames
 * 
 * 
 */
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

import org.apache.log4j.Logger;
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
import com.java.entity.Model;
import com.java.services.ModelService;
import com.java.services.ModelServiceImpl;
import com.java.servlet.appconfig.AppConfigurationServlet;
import com.java.services.ApplicationService;
import com.java.services.ApplicationServiceImpl;
import com.java.entity.Status;
import com.java.entity.Application;
import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Users;

/**
 * Servlet implementation class InputRawBas
 */
public class ApplicationManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(AppConfigurationServlet.class);

	/* Service variable */
	ApplicationService service = new ApplicationServiceImpl();
	/* Object Mapper to map JSON object to Application class object */
	ObjectMapper objectMapper = new ObjectMapper();

	// Status to return
	Status status = Status.SUCCESS;
	String logged_user;

	public ApplicationManagementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
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
		if (session != null) {
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			logged_user = (String) request.getSession(false).getAttribute("user_name");

			String func = request.getParameter("func");
			if (func.equals("add")) {
				add(request, response);
			} else if (func.equals("edit")) {
				edit(request, response);
			} else if (func.equals("delete")) {
				delete(request, response);
			} else if (func.equals("chng_appln")) { // Function to change
													// application number in
													// session
				changeApplicationNo(request, response);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
			// response.sendRedirect("login.jsp");
		}

	}

	private void get(HttpServletRequest request, HttpServletResponse response) {
		try {
			String strt = request.getParameter("start");
			String lim = request.getParameter("limit");

			int start_index = Integer.parseInt(strt);
			int limit = Integer.parseInt(lim);

			String filters_array = request.getParameter("filter");
			List<Filter> filters = null;
			if (filters_array != null) {
				filters = Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
			}

			ArrayList<Application> applications = null;
			applications = (ArrayList<Application>) service.getAll(logged_user, start_index, limit, filters);

			int total_results = service.totalCount(logged_user, filters);

			if (applications != null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				PagedApplication paged_applications = new PagedApplication();
				paged_applications.setTotal_count(total_results);
				paged_applications.setData(applications);
				objectMapper.writeValue(response.getOutputStream(), paged_applications);
			}
		} catch (Exception e) {
			log.error("Unable to load data");
		}
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) {
		try {

			ArrayList<Application> applications = null;
			applications = (ArrayList<Application>) service.getAll(logged_user);

			// int total_results = service.totalCount(logged_user);

			if (applications != null) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				objectMapper.writeValue(response.getOutputStream(), applications);
			}
		} catch (Exception e) {
			log.error("Unable to load data");
		}
	}
	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Application> applications = Arrays.asList(objectMapper.readValue(request.getInputStream(), Application[].class));

		// Validation
		
		//Saving
		for (int i = 0; i < applications.size(); i++) {
			Application new_application = applications.get(i);
			// reset status
			new_application.setApplication_status("");
			new_application.setCreated_date(new Date());
			new_application.setCreated_user(logged_user);
			new_application.setUpdated_date(new Date());
			Status status = service.addNewApplication(new_application);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
			
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Application> applications = Arrays
				.asList(objectMapper.readValue(request.getInputStream(), Application[].class));
		for (int i = 0; i < applications.size(); i++) {
			// Delete will also be sent as a update request

			Application application = applications.get(i);

			if (application.getApplication_status().equals("D")) {
				// Reset the status
				application.setApplication_status("");

				service.remove(application);
			} else if (application.getApplication_status().equals("M")) {
				application.setApplication_status("");
				application.setUpdated_date(new Date());
				service.editApplication(application);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			List<Application> applications = Arrays.asList(objectMapper.readValue(request.getInputStream(), Application[].class));

			// Validation
			for (int i = 0; i < applications.size(); i++) {
				service.remove(applications.get(i));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Change Application Number in Session
	private void changeApplicationNo(HttpServletRequest request, HttpServletResponse response) {

		String application_no = request.getParameter("application_no");
		String application_name = request.getParameter("application_name");

		HttpSession session = request.getSession();

		session.setAttribute("application_no", application_no);
		session.setAttribute("application_name", application_name);

	}

	// class created for pagination
	private class PagedApplication {
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
	}

}
