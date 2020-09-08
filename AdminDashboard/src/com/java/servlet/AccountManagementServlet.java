package com.java.servlet;

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
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.*;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyleInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.services.AccountService;
import com.java.services.AccountServiceImpl;
import com.java.services.AppLoginService;
import com.java.services.AppLoginServiceImpl;
import com.java.services.NatureService;
import com.java.services.NatureServiceImpl;
import com.java.services.TypeService;
import com.java.services.TypeServiceImpl;
import com.java.entity.Status;
import com.java.entity.Type;
import com.java.entity.Users;
import com.java.entity.Account;
import com.java.pojo.Filter;
import com.java.entity.Nature;
import com.java.entity.Scenario;

/**
 * Servlet implementation class InputRawBas
 */
public class AccountManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service variable

	AccountService service = new AccountServiceImpl();
	NatureService nature_service = new NatureServiceImpl();
	TypeService type_service = new TypeServiceImpl();
	
	// Object Mapper to map JSON object to Account class object
	ObjectMapper objectMapper = new ObjectMapper();

	// Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no = "";

	public AccountManagementServlet() {

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
			} else if (func.equals("export_data")) {
				exportData(request, response);
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

		ArrayList<Account> accounts = null;
			accounts = (ArrayList<Account>) service.getAll(application_no,start_index,limit,filters);

			int total_results = service.totalCount(logged_user,filters);
			
			
			if(accounts !=null){ 
		        response.setContentType("application/json");            
		        response.setCharacterEncoding("UTF-8");
		      
		        PagedAccounts paged_applications = new PagedAccounts();
		        paged_applications.setTotal_count(total_results);
		        paged_applications.setData(accounts);
		        objectMapper.writeValue(response.getOutputStream(), paged_applications);
			  	}

	}
	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Account> accounts = Arrays.asList(objectMapper.readValue(request.getInputStream(), Account[].class));

		// Validation
		for (int i = 0; i < accounts.size(); i++) {
			Account new_account = accounts.get(i);
			// reset status
			new_account.setAccount_status("");
			new_account.setCreated_date(new Date());
			new_account.setCreated_user(logged_user);
			new_account.setUpdated_date(new Date());
			new_account.setApplication_no(application_no);
			Status status = service.addNewAccount(new_account);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Account> accounts = Arrays.asList(objectMapper.readValue(request.getInputStream(), Account[].class));

		for (int i = 0; i < accounts.size(); i++) {
			// Delete will also be sent as a update request
			Account account = accounts.get(i);

			if (account.getAccount_status().equals("D")) {
				// Reset the status
				account.setAccount_status("");

				service.remove(account);
			} else if (account.getAccount_status().equals("M")) {
				account.setAccount_status("");
				account.setUpdated_date(new Date());
				service.editAccount(account);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			List<Account> accounts = Arrays.asList(objectMapper.readValue(request.getInputStream(), Account[].class));

			// Validation
			for (int i = 0; i < accounts.size(); i++) {
				service.remove(accounts.get(i));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void exportData(HttpServletRequest request, HttpServletResponse response) {
		
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			try {
				filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String title = request.getParameter("menu_title");
		// header
		String menu_name = "Account";
		if (title != null) {
			menu_name = title;
		}
		ArrayList<Account> accounts = null;
		accounts = (ArrayList<Account>) service.getAll(application_no,0,-1,filters);

		ArrayList<Nature> nature = (ArrayList<Nature>) nature_service.getAll();
		
		ArrayList<Type> type = (ArrayList<Type>) type_service.getAll();
		
		try {
			 XSSFWorkbook workbook = new XSSFWorkbook();
			 XSSFSheet sheet = workbook.createSheet("items");
			 XSSFTable table = sheet.createTable();
			 CTTable cttable = table.getCTTable();

			 cttable.setDisplayName("Table1");
			 cttable.setId(1);
			 cttable.setName("Table1");
			 //Reference from A1:B[noOfrecords]
			 int s =nature.size()+1;
			 String ref = "A1:B"+s;
			 cttable.setRef(ref);
			 cttable.setTotalsRowShown(false);

			 CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
			 styleInfo.setName("TableStyleMedium2");
			 styleInfo.setShowColumnStripes(false);
			 styleInfo.setShowRowStripes(true);

			 CTTableColumns columns = cttable.addNewTableColumns();
			 columns.setCount(2);
			 
			 CTTableColumn column = columns.addNewTableColumn();
			 column.setId(1);
			 column.setName("nature_desc");
			 
			 CTTableColumn column2 = columns.addNewTableColumn();
			 column2.setId(2);
			 column2.setName("nature_code");
			 
			 XSSFRow row_header = sheet.createRow(0);
	    	 XSSFCell cell1 = row_header.createCell(0);
	    	 
	    	 XSSFCell cell2 = row_header.createCell(1);
			 cell1.setCellValue("nature_desc"); //content **must** be here for table column names
			 cell2.setCellValue("nature_code");
		
			 int j = 0;
			 int index = 0;
			for (int i = 1; i < nature.size() + 1; i++) { // as index is
																// staring from 2
					Nature nat = nature.get(index);
					XSSFRow row = sheet.createRow(i);
					row.createCell(j++).setCellValue(nat.getDescription());
					row.createCell(j++).setCellValue(nat.getNature_code());
					j = 0;
					index++;
				}
	//------------Type sheet-------//
			 XSSFSheet sheet3 = workbook.createSheet("typeitems");
			 XSSFTable table3 = sheet3.createTable();
			 CTTable cttable3 = table3.getCTTable();

			 cttable3.setDisplayName("Table3");
			 cttable3.setId(3);
			 cttable3.setName("Table3");
			 //Reference from A1:B[noOfrecords]
			 s =type.size()+1;
			 ref = "A1:B"+s;
			 cttable3.setRef(ref);
			 cttable3.setTotalsRowShown(false);

			 CTTableStyleInfo styleInfo3 = cttable3.addNewTableStyleInfo();
			 styleInfo3.setName("TableStyleMedium2");
			 styleInfo3.setShowColumnStripes(false);
			 styleInfo3.setShowRowStripes(true);

			 CTTableColumns cs = cttable3.addNewTableColumns();
			 cs.setCount(2);
			 
			 CTTableColumn c1 = cs.addNewTableColumn();
			 c1.setId(1);
			 c1.setName("description");
			 
			 CTTableColumn c2 = cs.addNewTableColumn();
			 c2.setId(2);
			 c2.setName("type_code");
			 
			 XSSFRow row_header2 = sheet3.createRow(0);
	   	 XSSFCell ce1 = row_header2.createCell(0);
	   	 
	   	 XSSFCell ce2 = row_header2.createCell(1);
			 ce1.setCellValue("description"); //content **must** be here for table column names
			 ce2.setCellValue("type_code");
		
			 j = 0;
			 index = 0;
			for (int i = 1; i < type.size() + 1; i++) { // as index is
																// staring from 2
					Type t = type.get(index);
					XSSFRow row = sheet3.createRow(i);
					row.createCell(j++).setCellValue(t.getDescription());
					row.createCell(j++).setCellValue(t.getType_code());
					j = 0;
					index++;
				}
	//---creating a named reference
				XSSFName name = workbook.createName();
				 name.setNameName("test");
				 name.setRefersToFormula("Table1[nature_desc]"); 
				 
				 XSSFName name2 = workbook.createName();
				 name2.setNameName("test2");
				 name2.setRefersToFormula("Table3[description]"); 
	//----------------------Sheet containing data-----------------//
				 XSSFSheet sheet2 = workbook.createSheet("Accounts Management");
				 XSSFTable table2 = sheet2.createTable();
				 CTTable cttable2 = table2.getCTTable();

				 cttable2.setDisplayName("Table2");
				 cttable2.setId(2);
				 cttable2.setName("Table2");
				 //Reference from A1:B[noOfrecords]
				 int s2 =accounts.size()+2;
				 String ref2 = "A2:Q"+s2;
				 cttable2.setRef(ref2);
				 cttable2.setTotalsRowShown(false);

				 CTTableStyleInfo styleInfo2 = cttable2.addNewTableStyleInfo();
				 styleInfo2.setName("TableStyleMedium2");
				 styleInfo2.setShowColumnStripes(false);
				 styleInfo2.setShowRowStripes(true);

				//---------create columns-------------------------//
				 CTTableColumns columns2 = cttable2.addNewTableColumns();
				 columns2.setCount(18);
				 
				 CTTableColumn col = columns2.addNewTableColumn();
				 col.setId(1);
				 col.setName("account_code");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(2);
				 col.setName("account_description");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(3);
				 col.setName("alternate_description");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(4);
				 col.setName("nature");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(5);
				 col.setName("type");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(6);
				 col.setName("account_attr1");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(7);
				 col.setName("account_attr2");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(8);
				 col.setName("account_attr3");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(9);
				 col.setName("account_attr4");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(10);
				 col.setName("account_attr5");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(11);
				 col.setName("account_attr6");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(12);
				 col.setName("account_attr7");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(13);
				 col.setName("account_attr8");

				 col = columns2.addNewTableColumn();
				 col.setId(14);
				 col.setName("account_attr9");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(15);
				 col.setName("account_attr10");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(16);
				 col.setName("nature_code");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(17);
				 col.setName("type_code");

				  //----hidden code header row----------//
				 XSSFRow row_code = sheet2.createRow(0);
				 
				 row_code.createCell(0).setCellValue("account_code");
					row_code.createCell(1).setCellValue("account_description");
					row_code.createCell(2).setCellValue("alternate_description");
					row_code.createCell(3).setCellValue("nature");
					row_code.createCell(4).setCellValue("type");
					row_code.createCell(5).setCellValue("account_attr1");
					row_code.createCell(6).setCellValue("account_attr2");
					row_code.createCell(7).setCellValue("account_attr3");
					row_code.createCell(8).setCellValue("account_attr4");
					row_code.createCell(9).setCellValue("account_attr5");
					row_code.createCell(10).setCellValue("account_attr6");
					row_code.createCell(11).setCellValue("account_attr7");
					row_code.createCell(12).setCellValue("account_attr8");
					row_code.createCell(13).setCellValue("account_attr9");
					row_code.createCell(14).setCellValue("account_attr10");
					
					row_code.createCell(15).setCellValue("nature_code");
					row_code.createCell(16).setCellValue("type_code");
					
					CellStyle hiddenstyle = workbook.createCellStyle();
					hiddenstyle.setHidden(true);
					row_code.setRowStyle(hiddenstyle);

					row_code.setZeroHeight(true);
		           //-----------Visible header row--------------//
					row_header = sheet2.createRow(1);
					row_header.createCell(0).setCellValue(menu_name + " Management");
					row_header.createCell(1).setCellValue(menu_name + " Description");
					row_header.createCell(2).setCellValue("Alternate Description");
					row_header.createCell(3).setCellValue("Nature");
					row_header.createCell(4).setCellValue("Type");
					row_header.createCell(5).setCellValue(menu_name + " Attribute 1");
					row_header.createCell(6).setCellValue(menu_name + " Attribute 2");
					row_header.createCell(7).setCellValue(menu_name + " Attribute 3");
					row_header.createCell(8).setCellValue(menu_name + " Attribute 4");
					row_header.createCell(9).setCellValue(menu_name + " Attribute 5");
					row_header.createCell(10).setCellValue(menu_name + " Attribute 6");
					row_header.createCell(11).setCellValue(menu_name + " Attribute 7");
					row_header.createCell(12).setCellValue(menu_name + " Attribute 8");
					row_header.createCell(13).setCellValue(menu_name + " Attribute 9");
					row_header.createCell(14).setCellValue(menu_name + " Attribute 10");
		  		
					row_header.createCell(15).setCellValue("nature code");
					row_header.createCell(16).setCellValue("type code");
			  		
					for(int i=0;i<17;i++){
			        	  sheet2.autoSizeColumn(i);
			        }
		        	//set nature desc to a drop down list from above table
					//step 1: convert above sheet data to a table
					//step 2: use table name to create a Name
					//step 3: use name to create dropdown list in column description
					
		        	//data validations
					   DataValidationHelper dvHelper = sheet.getDataValidationHelper();
					   DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("test");
					   CellRangeAddressList addressList = new CellRangeAddressList(2, accounts.size()+2, 3, 3);            
					   DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
					   sheet2.addValidationData(validation);
					   
					   DataValidationHelper dvHelper2 = sheet.getDataValidationHelper();
					   DataValidationConstraint dvConstraint2 = dvHelper2.createFormulaListConstraint("test2");
					   CellRangeAddressList addressList2 = new CellRangeAddressList(2, accounts.size()+2, 4, 4);            
					   DataValidation validation2 = dvHelper2.createValidation(dvConstraint2, addressList2);
					   sheet2.addValidationData(validation2);
					   
		        	//Add data
		        	j = 0;
					index = 0;
					for (int i = 2; i < accounts.size() + 2; i++) { // as index is
																	// staring from 2
						Account account = accounts.get(index);

						XSSFRow row = sheet2.createRow(i);
						row.createCell(j++).setCellValue(account.getAccount_code());
						row.createCell(j++).setCellValue(account.getAccount_description());
						row.createCell(j++).setCellValue(account.getAlternate_description());
						row.createCell(j++).setCellValue(account.getNature().getDescription());
						row.createCell(j++).setCellValue(account.getType().getDescription());
						row.createCell(j++).setCellValue(account.getAccount_attr1());
						row.createCell(j++).setCellValue(account.getAccount_attr2());
						row.createCell(j++).setCellValue(account.getAccount_attr3());
						row.createCell(j++).setCellValue(account.getAccount_attr4());
						row.createCell(j++).setCellValue(account.getAccount_attr5());
						row.createCell(j++).setCellValue(account.getAccount_attr6());
						row.createCell(j++).setCellValue(account.getAccount_attr7());
						row.createCell(j++).setCellValue(account.getAccount_attr8());
						row.createCell(j++).setCellValue(account.getAccount_attr9());
						row.createCell(j++).setCellValue(account.getAccount_attr10());
						
						//Add hidden code values for combo boxes
						XSSFCell c=row.createCell(j++);
						c.setCellValue(account.getNature().getNature_code());
						//set each nature code to vlookup value
						int size = nature.size()+1;
						int v = i+1;
						String formula = "IFERROR(VLOOKUP(D"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
						c.setCellFormula(formula);
						
						XSSFCell t=row.createCell(j++);
						t.setCellValue(account.getType().getType_code());
						size = type.size()+1;
						v = i+1;
						formula = "IFERROR(VLOOKUP(E"+v+",typeitems!$A$2:$B$"+size+",2,0),\"\")";
						t.setCellFormula(formula);
						
						j = 0;
						index++;
					}
					//hide the code columns
					sheet2.setColumnHidden(15, true);
					sheet2.setColumnHidden(16, true);
					
					workbook.getSheetAt(0).setSelected(false);
					workbook.setActiveSheet(2);
					workbook.setSheetHidden(0, true);
					workbook.setSheetHidden(1, true);

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=" + menu_name + "_management.xlsx");

			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// class created for pagination
	private class PagedAccounts {
		int total_count;
		List<Account> data;

		public int getTotal_count() {
			return total_count;
		}

		public void setTotal_count(int total_count) {
			this.total_count = total_count;
		}

		public List<Account> getData() {
			return data;
		}

		public void setData(List<Account> data) {
			this.data = data;
		}
	}
}
