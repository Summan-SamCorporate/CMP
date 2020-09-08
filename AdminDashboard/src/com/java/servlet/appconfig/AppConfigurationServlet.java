package com.java.servlet.appconfig;

/*
 * Created By: Summan Bahadur
 * Description: Servlet called for App Configuration in accordion 
 * Reference/s: appconfiguration frames
 * 
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.entity.AppDimensionsConfig;
import com.java.entity.Menu_Config;
import com.java.entity.Users;
import com.java.services.AppConfigurationsService;
import com.java.services.AppConfigurationsServiceImpl;
import com.java.services.UsersService;
import com.java.services.UsersServiceImpl;


@WebServlet("/AppConfigurationServlet")
public class AppConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static Logger log = Logger.getLogger(AppConfigurationServlet.class);
	
	Users logged_user = null;
	AppConfigurationsService service = new AppConfigurationsServiceImpl();
	String application_no ="";
	ObjectMapper objectMapper = new ObjectMapper();
	
	
    public AppConfigurationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			application_no = (String) session.getAttribute("application_no");
			
			String func = request.getParameter("func");
			if (func.equals("appdimension")) {
				getCustomDimensions(request, response);

			}
			else if (func.equals("appdimensionvisible")) {
				getVisibleCustomDimensions(request, response);

			}
			else if (func.equals("appdimensionvisiblebud")) {
				//Exclude Entity and Accounts in Custom Dimensions in Budgeting frame
				getVisibleCustomDimensionsBudgeting(request, response);

			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	private void getCustomDimensions(HttpServletRequest request, HttpServletResponse response) {
		
		try{
			ArrayList<AppDimensionsConfig> dimensions=(ArrayList<AppDimensionsConfig>) service.listAll(application_no);	
			if(dimensions != null){
				response.setContentType("application/json");            
			    response.setCharacterEncoding("UTF-8");
				objectMapper.writeValue(response.getOutputStream(), dimensions);
				}
		}catch(Exception e){
			log.error("Unable to load data");
		}
	}
	private void getVisibleCustomDimensions(HttpServletRequest request, HttpServletResponse response) {
		try{
			 response.setContentType("application/json");            
		     response.setCharacterEncoding("UTF-8");
		     
			ArrayList<AppDimensionsConfig> dimensions=(ArrayList<AppDimensionsConfig>) service.listVisible(application_no);		
			objectMapper.writeValue(response.getOutputStream(), dimensions);
		}catch(Exception e){
			log.error("Unable to load data");
		}
	}

	
	private void getVisibleCustomDimensionsBudgeting(HttpServletRequest request, HttpServletResponse response) {
		try{
			 response.setContentType("application/json");            
		     response.setCharacterEncoding("UTF-8");
		     
		     //List all visible dimensions excluding blocked dimensions
			ArrayList<AppDimensionsConfig> dimensions=(ArrayList<AppDimensionsConfig>) service.listCustomDimensions(application_no);		
			objectMapper.writeValue(response.getOutputStream(), dimensions);
		}catch(Exception e){
			log.error("Unable to load data");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Read xml file find required tag and update it
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		
		List<AppDimensionsConfig> changed_items = Arrays
				.asList(objectMapper.readValue(request.getInputStream(), AppDimensionsConfig[].class));

		// save changes in database
		for (int i = 0; i < changed_items.size(); i++) {
			service.edit(changed_items.get(i));
		}
	}
	

}
