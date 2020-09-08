package com.java.servlet;

import java.util.List;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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

@WebServlet("/BudgetServlet")
public class BudgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ObjectMapper objectMapper = new ObjectMapper();
	SystemConfigurationsService service = new SystemConfigurationsServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BudgetServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Budget> budgets = new ArrayList<Budget>();
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ISO_LOCAL_DATE;

		/* Initial default data - to set up the grid table */
		
		LocalDateTime date = LocalDateTime.of(2019, Month.JANUARY, 28, 14, 33);
	    String formattedDate = date.format(myFormatObj);
		Budget b1 = new Budget(1,1,"Sales Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.FEBRUARY, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b2 = new Budget(2,2,"Sales Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.MARCH, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b3 = new Budget(3,3,"Sales Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
	
		date = LocalDateTime.of(2019, Month.APRIL, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b4 = new Budget(4,4,"Sales Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.MAY, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b5 = new Budget(5,5,"Sales Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.JUNE, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b6 = new Budget(6,6,"Sales Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.JULY, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b7 = new Budget(7,7,"Sales Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.AUGUST, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b8 = new Budget(8,8,"Sales Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.SEPTEMBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b9 = new Budget(9,9,"Sales Revenue","Department 1","Division","Product2","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.OCTOBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b10 = new Budget(10,10,"Sales Revenue","Department 2","Division","Product2","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.NOVEMBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b11 = new Budget(11,11,"Sales Revenue","Department 1","Division","Product2","Brand3",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.DECEMBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b12 = new Budget(12,12,"Sales Revenue","Department 1","Division","Product2","Brand3",formattedDate,(float) 600.0);
	
	    date = LocalDateTime.of(2019, Month.JANUARY, 28, 14, 33);
	    formattedDate = date.format(myFormatObj);
		Budget b13 = new Budget(13,1,"Sales Revenue","Department 2","Division","Product2","Brand",formattedDate,(float) 600.0);
	
		//--------------Service Revenue
		date = LocalDateTime.of(2019, Month.JANUARY, 28, 14, 33);
	    formattedDate = date.format(myFormatObj);
		Budget b14 = new Budget(14,1,"Service Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.FEBRUARY, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b15 = new Budget(15,2,"Service Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.MARCH, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b16 = new Budget(16,3,"Service Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
	
		date = LocalDateTime.of(2019, Month.APRIL, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b17 = new Budget(17,4,"Service Revenue","Department 1","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.MAY, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b18 = new Budget(18,5,"Service Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.JUNE, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b19 = new Budget(19,6,"Service Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.JULY, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b20 = new Budget(20,7,"Service Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.AUGUST, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b21 = new Budget(21,8,"Service Revenue","Department 2","Division","Product","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.SEPTEMBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b22 = new Budget(22,9,"Service Revenue","Department 1","Division","Product2","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.OCTOBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b23 = new Budget(23,10,"Service Revenue","Department 2","Division","Product2","Brand",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.NOVEMBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b24 = new Budget(24,11,"Service Revenue","Department 1","Division","Product2","Brand3",formattedDate,(float) 600.0);
		
		date = LocalDateTime.of(2019, Month.DECEMBER, 28, 14, 33);
		formattedDate = date.format(myFormatObj);
		Budget b25 = new Budget(25,12,"Service Revenue","Department 1","Division","Product2","Brand3",formattedDate,(float) 600.0);
		
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		budgets.add(b1);
		budgets.add(b2);
		budgets.add(b3);
		budgets.add(b4);
		budgets.add(b5);
		budgets.add(b6);
		budgets.add(b7);
		budgets.add(b8);
		budgets.add(b9);
		budgets.add(b10);
		budgets.add(b11);
		budgets.add(b12);
		budgets.add(b13);
		budgets.add(b14);
		budgets.add(b15);
		budgets.add(b16);
		budgets.add(b17);
		budgets.add(b18);
		budgets.add(b19);
		budgets.add(b20);
		budgets.add(b21);
		budgets.add(b22);
		budgets.add(b23);
		budgets.add(b24);
		budgets.add(b25);
		
		objectMapper.writeValue(response.getOutputStream(), budgets);
			
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
class Budget {
	
	
	public Budget(int id,int month_id, String account, String department, String division, String product, String brand, String date,
			Float value) {
		super();
		this.id = id;
		this.month_id = month_id;
		this.account = account;
		this.department = department;
		this.division = division;
		this.product = product;
		this.brand = brand;
		this.date = date;
		this.value = value;
	}
	int id;
	int month_id;
	String account;
	String department;
	String division;
	String product;
	String brand;
	String date;
/*	String year;
*/	Float value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/*public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}*/
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getMonth_id() {
		return month_id;
	}
	public void setMonth_id(int month_id) {
		this.month_id = month_id;
	}
}
