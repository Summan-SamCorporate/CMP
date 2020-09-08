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

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddressList;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.java.services.CurrencyService;
import com.java.services.CurrencyServiceImpl;
import com.java.services.EntityService;
import com.java.services.EntityServiceImpl;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.Currency;
import com.java.entity.Entities;
import com.java.pojo.Filter;
import com.java.entity.Nature;

/**
 * Servlet implementation class InputRawBas
 */
public class EntityManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	EntityService service = new EntityServiceImpl();
	CurrencyService currency_service = new CurrencyServiceImpl();
	//Object Mapper to map JSON object to Entity class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no ="";
	
	
    public EntityManagementServlet() {
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
			
			// Get logged in user from session
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
		//filter data with application number
		ArrayList<Entities> entities=(ArrayList<Entities>) service.getAll(application_no,start_index,limit,filters);		
		
		int total_results = service.totalCount(application_no,filters);
		
		
		if(entities !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        PagedEntities paged_entity = new PagedEntities();
	        paged_entity.setTotal_count(total_results);
	        paged_entity.setData(entities);
	        objectMapper.writeValue(response.getOutputStream(), paged_entity);
		   
	        objectMapper.writeValue(response.getOutputStream(), entities);
		}
	    
	}
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Entities> entities = Arrays.asList(objectMapper.readValue(request.getInputStream(), Entities[].class));
		
		//Validation 
		for(int i=0;i<entities.size();i++){
			Entities new_entity = entities.get(i);
		//reset status
			new_entity.setColumn_status("");
			new_entity.setCreated_date(new Date());
			new_entity.setCreated_user(logged_user);
			new_entity.setUpdated_date(new Date());
			new_entity.setApplication_no(application_no);
			
			Status status =service.addNewEntity(new_entity);
			if(status.getCode()==2){ //NonUniqueKey
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		
			}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Entities> entities = Arrays.asList(objectMapper.readValue(request.getInputStream(), Entities[].class));
		for(int i=0;i<entities.size();i++){
		//Delete will also be sent as a update request
		
			Entities entity = entities.get(i);
			
		if(entity.getColumn_status().equals("D")){
			//Reset the status
			entity.setColumn_status("");
			
			service.remove(entity);
		}else if(entity.getColumn_status().equals("M")){
			entity.setColumn_status("");
			entity.setUpdated_date(new Date());
			service.editEntity(entity);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try{
		List<Entities> entities = Arrays.asList(objectMapper.readValue(request.getInputStream(), Entities[].class));
	
		//Validation 
		for(int i=0;i<entities.size();i++){
		service.remove(entities.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}
		
		
	}
	private void exportData(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		
		String title =request.getParameter("menu_title");
		//header
	    String menu_name = "Entity";
	    if(title!=null){
	    	menu_name=title;
	    }
		ArrayList<Entities> entities= null;
		entities =(ArrayList<Entities>) service.getAll(application_no,0,-1,filters);	

		
		ArrayList<Currency> currency = (ArrayList<Currency>) currency_service.getAll(application_no);
		
		try {
			    XSSFWorkbook workbook = new XSSFWorkbook();
				 XSSFSheet sheet = workbook.createSheet("items");
				 XSSFTable table = sheet.createTable();
				 CTTable cttable = table.getCTTable();

				 cttable.setDisplayName("Table1");
				 cttable.setId(1);
				 cttable.setName("Table1");
				 //Reference from A1:B[noOfrecords]
				 int s =currency.size()+1;
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
				 column.setName("currency_description");
				 
				 CTTableColumn column2 = columns.addNewTableColumn();
				 column2.setId(2);
				 column2.setName("currency_code");
				 
				 XSSFRow row_header = sheet.createRow(0);
		    	 XSSFCell cell1 = row_header.createCell(0);
		    	 
		    	 XSSFCell cell2 = row_header.createCell(1);
				 cell1.setCellValue("currency_description"); //content **must** be here for table column names
				 cell2.setCellValue("currency_code");
			
				 int j = 0;
				 int index = 0;
				for (int i = 1; i < currency.size() + 1; i++) { // as index is
																	// staring from 2
						Currency nat = currency.get(index);
						XSSFRow row = sheet.createRow(i);
						row.createCell(j++).setCellValue(nat.getCurrency_description());
						row.createCell(j++).setCellValue(nat.getCurrency_code());
						j = 0;
						index++;
					}
				//---creating a named reference
				XSSFName name = workbook.createName();
				 name.setNameName("test");
				 name.setRefersToFormula("Table1[currency_description]"); 
	
				//-------------------------
			    
			    XSSFSheet sheet2 = workbook.createSheet(menu_name+" Management");  

			    XSSFTable table2 = sheet2.createTable();
				 CTTable cttable2 = table2.getCTTable();

				 cttable2.setDisplayName("Table2");
				 cttable2.setId(2);
				 cttable2.setName("Table2");
				 //Reference from A1:B[noOfrecords]
				 int s2 =entities.size()+2;
				 String ref2 = "A2:T"+s2;
				 cttable2.setRef(ref2);
				 cttable2.setTotalsRowShown(false);

				 CTTableStyleInfo styleInfo2 = cttable2.addNewTableStyleInfo();
				 styleInfo2.setName("TableStyleMedium2");
				 styleInfo2.setShowColumnStripes(false);
				 styleInfo2.setShowRowStripes(true);

				//---------create columns-------------------------//
				 CTTableColumns columns2 = cttable2.addNewTableColumns();
				 columns2.setCount(21);
				 
				 CTTableColumn col = columns2.addNewTableColumn();
				 col.setId(1);
				 col.setName("entity_code");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(2);
				 col.setName("entity_description");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(3);
				 col.setName("entity_currency");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(4);
				 col.setName("business_name");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(5);
				 col.setName("legal_hq_name");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(6);
				 col.setName("legal_hq_location");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(7);
				 col.setName("administrative_hq");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(8);
				 col.setName("entity_location");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(9);
				 col.setName("main_office");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(10);
				 col.setName("manager");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(11);
				 col.setName("entity_email");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(12);
				 col.setName("entity_phone");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(13);
				 col.setName("entity_country");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(14);
				 col.setName("entity_attr1");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(15);
				 col.setName("entity_attr2");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(16);
				 col.setName("entity_attr3");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(17);
				 col.setName("entity_attr4");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(18);
				 col.setName("entity_attr5");
				 
				 col = columns2.addNewTableColumn();
				 col.setId(19);
				 col.setName("entity_attr6");
				
				 col = columns2.addNewTableColumn();
				 col.setId(20);
				 col.setName("currency_code");
				
				  //----hidden code header row----------//
				 XSSFRow row_code = sheet2.createRow(0);
			
				 row_code.createCell(0).setCellValue("entity_code");
		        row_code.createCell(1).setCellValue("entity_description");
		        row_code.createCell(2).setCellValue("entity_currency");
		        row_code.createCell(3).setCellValue("business_name");
		        row_code.createCell(4).setCellValue("legal_hq_name");
		        row_code.createCell(5).setCellValue("legal_hq_location");
		        row_code.createCell(6).setCellValue("administrative_hq");
		        row_code.createCell(7).setCellValue("entity_location");
		        row_code.createCell(8).setCellValue("main_office");
		        row_code.createCell(9).setCellValue("manager");
		        row_code.createCell(10).setCellValue("entity_email");
		        row_code.createCell(11).setCellValue("entity_phone");
		        row_code.createCell(12).setCellValue("entity_country");
		        row_code.createCell(13).setCellValue("entity_attr1");
		        row_code.createCell(14).setCellValue("entity_attr2");
		        row_code.createCell(15).setCellValue("entity_attr3");
		        row_code.createCell(16).setCellValue("entity_attr4");
		        row_code.createCell(17).setCellValue("entity_attr5");
		        row_code.createCell(18).setCellValue("entity_attr6");
		        
		        row_code.createCell(19).setCellValue("currency_code");
			       
		        
		        CellStyle hiddenstyle = workbook.createCellStyle();
		        hiddenstyle.setHidden(true);	
		        row_code.setRowStyle(hiddenstyle);

		        row_code.setZeroHeight(true);
		       

		       //-----------Visible header row--------------//
				row_header = sheet2.createRow(1);
		        row_header.createCell(0).setCellValue(menu_name+" Management");
		        row_header.createCell(1).setCellValue(menu_name+" Description");
		        row_header.createCell(2).setCellValue(menu_name+" Currency");
		        row_header.createCell(3).setCellValue("Business Name");
		        row_header.createCell(4).setCellValue("Legal HQ Name");
		        row_header.createCell(5).setCellValue("Legal HQ Description");
		        row_header.createCell(6).setCellValue("Administrative HQ");
		        row_header.createCell(7).setCellValue(menu_name+" Location");
		        row_header.createCell(8).setCellValue("Main Office");
		        row_header.createCell(9).setCellValue("Manager");
		        row_header.createCell(10).setCellValue(menu_name+" Email");
		        row_header.createCell(11).setCellValue(menu_name+" Phone");
		        row_header.createCell(12).setCellValue(menu_name+" Country");
		        row_header.createCell(13).setCellValue(menu_name+" Attribute 1");
		        row_header.createCell(14).setCellValue(menu_name+" Attribute 2");
		        row_header.createCell(15).setCellValue(menu_name+" Attribute 3");
		        row_header.createCell(16).setCellValue(menu_name+" Attribute 4");
		        row_header.createCell(17).setCellValue(menu_name+" Attribute 5");
		        row_header.createCell(18).setCellValue(menu_name+" Attribute 6");
		        
		    	row_header.createCell(19).setCellValue("Currency code");
			  	
		    	for(int i=0;i<20;i++){
		        	  sheet2.autoSizeColumn(i);
		        }
	        	//set nature desc to a drop down list from above table
				//step 1: convert above sheet data to a table
				//step 2: use table name to create a Name
				//step 3: use name to create dropdown list in column description
				
	        	//data validations
				   DataValidationHelper dvHelper = sheet.getDataValidationHelper();
				   DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint("test");
				   CellRangeAddressList addressList = new CellRangeAddressList(2, entities.size()+2, 2, 2);            
				   DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
				   sheet2.addValidationData(validation);
		        
		        
			     j=0;
			    index=0;
		        for(int i=2;i<entities.size()+2;i++){   //as index is staring from 2
		        Entities entity = entities.get(index);
		        
		        XSSFRow row = sheet2.createRow(i);
		        row.createCell(j++).setCellValue(entity.getEntity_code());
		        row.createCell(j++).setCellValue(entity.getEntity_description());
		        row.createCell(j++).setCellValue(entity.getCurrency().getCurrency_description());
		        row.createCell(j++).setCellValue(entity.getBusiness_name());
		        row.createCell(j++).setCellValue(entity.getLegal_hq_name());
		        row.createCell(j++).setCellValue(entity.getLegal_hq_location());
		        row.createCell(j++).setCellValue(entity.getAdministrative_hq());
		        row.createCell(j++).setCellValue(entity.getEntity_location());
		        row.createCell(j++).setCellValue(entity.getMain_office());
		        row.createCell(j++).setCellValue(entity.getManager());
		        row.createCell(j++).setCellValue(entity.getEntity_email());
		        row.createCell(j++).setCellValue(entity.getEntity_phone());
		        row.createCell(j++).setCellValue(entity.getEntity_country());
		        row.createCell(j++).setCellValue(entity.getEntity_attr1());
		        row.createCell(j++).setCellValue(entity.getEntity_attr2());
		        row.createCell(j++).setCellValue(entity.getEntity_attr3());
		        row.createCell(j++).setCellValue(entity.getEntity_attr4());
		        row.createCell(j++).setCellValue(entity.getEntity_attr5());
		        row.createCell(j++).setCellValue(entity.getEntity_attr6());
		        
		      //Add hidden code values for combo boxes
				XSSFCell c=row.createCell(j++);
				c.setCellValue(entity.getCurrency().getCurrency_code());
				//set each nature code to vlookup value
				int size = currency.size()+1;
				int v = i+1;
				String formula = "IFERROR(VLOOKUP(C"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
				c.setCellFormula(formula);
				
		        j=0;
		        index++;
		        }
		        
		      //hide the code columns
				sheet2.setColumnHidden(19, true);
				
				workbook.getSheetAt(0).setSelected(false);
				workbook.setActiveSheet(1);
				workbook.setSheetHidden(0, true);

			    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		        response.setHeader("Content-Disposition", "attachment; filename="+menu_name+"_management.xlsx");

		        ServletOutputStream out = response.getOutputStream();
		            workbook.write(out);
		            out.flush();
		            out.close();
		       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	  
		
	}

	//class created for pagination
		private class PagedEntities{
			int total_count;
			List<Entities> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<Entities> getData() {
				return data;
			}
			public void setData(List<Entities> data) {
				this.data = data;
			}
		}
	
}
		


