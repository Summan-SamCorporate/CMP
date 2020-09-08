package com.java.servlet;

import java.util.List;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.bean.Item;
import com.java.bean.Items;
import com.java.entity.Menu_Config;
import com.java.entity.Model;
import com.java.entity.Roles;
import com.java.entity.Users;
import com.java.services.AppLoginService;
import com.java.services.AppLoginServiceImpl;
import com.java.services.SystemConfigurationsService;
import com.java.services.SystemConfigurationsServiceImpl;
import com.java.services.UsersService;
import com.java.services.UsersServiceImpl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@WebServlet("/MainAccordionServlet")
public class MainAccordionServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ObjectMapper objectMapper = new ObjectMapper();
	SystemConfigurationsService service = new SystemConfigurationsServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainAccordionServlet2() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Menu_Config> menu_items = new ArrayList<Menu_Config>();
		ArrayList<Menu_Config> menu_items_all = new ArrayList<Menu_Config>();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String func = request.getParameter("func");

		if (func.equals("ma"))
		{
			menu_items = (ArrayList<Menu_Config>) service.listVisible();
			for (int i = 0; i < menu_items.size(); i++) {
				String customised_value = menu_items.get(i).getCustomised_value();
				customised_value = customised_value.replaceAll("\\s+", "");
				if (customised_value.equals("")) {
					menu_items.get(i).setCustomised_value(menu_items.get(i).getDefault_value());
					}
				}
			objectMapper.writeValue(response.getOutputStream(), menu_items);
			}
		else if (func.equals("maa")) {
			menu_items_all = (ArrayList<Menu_Config>) service.listAll();
			for (int i = 0; i < menu_items_all.size(); i++) {
				String customised_value = menu_items_all.get(i).getCustomised_value();
				customised_value = customised_value.replaceAll("\\s+", "");
				if (customised_value.equals("")) {
					menu_items_all.get(i).setCustomised_value(menu_items_all.get(i).getDefault_value());
					}
				}
			objectMapper.writeValue(response.getOutputStream(), menu_items_all);
			}
		else if (func.equals("gc")) {
			menu_items_all = (ArrayList<Menu_Config>) service.listAll();
			objectMapper.writeValue(response.getOutputStream(), menu_items_all);
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read xml file find required tag and update it
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<Menu_Config> changed_items = Arrays.asList(objectMapper.readValue(request.getInputStream(), Menu_Config[].class));

		// save changes in database
		for (int i = 0; i < changed_items.size(); i++) {
			service.edit(changed_items.get(i));
			}
		}
	}
