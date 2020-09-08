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
import com.java.entity.Account;
import com.java.pojo.Filter;
import com.java.entity.Nature;
import com.java.services.ScenarioService;
import com.java.services.ScenarioServiceImpl;
import com.java.entity.Status;
import com.java.entity.Type;
import com.java.entity.Period;
import com.java.entity.Scenario;

/**
 * Servlet implementation class InputRawBas
 */
public class ScenarioManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	ScenarioService service = new ScenarioServiceImpl();
	//Object Mapper to map JSON object to Scenario class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no ="";
	String version ="";
	
    public ScenarioManagementServlet() {
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
			if(func.equals("get_budget")){
				version = "BUDGET";
				System.out.println("server");
				get(request, response);
			}
			if(func.equals("get_act")){
				version="ACTUAL";
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
		if(version.equals("")){
		String strt = request.getParameter("start");
		String lim = request.getParameter("limit");
		
		int start_index = Integer.parseInt(strt);
		int limit = Integer.parseInt(lim);
		
		String filters_array = request.getParameter("filter");
		List<Filter> filters =null;
		if(filters_array != null){
			filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
		}
		
		
		ArrayList<Scenario> scenarioes = null;
		
		scenarioes= (ArrayList<Scenario>) service.getAll(application_no,start_index,limit,filters);		
		
		int total_results = service.totalCount(application_no,filters);
				
		if(scenarioes !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	      
	        PagedScenario paged_scenario = new PagedScenario();
	        paged_scenario.setTotal_count(total_results);
	        paged_scenario.setData(scenarioes);
	        objectMapper.writeValue(response.getOutputStream(), paged_scenario);
		   
		}
		}
		else{
			ArrayList<Scenario> scenarioes = (ArrayList<Scenario>) service.getAll(application_no,version);	

	        objectMapper.writeValue(response.getOutputStream(), scenarioes);
			
		}
	    
	}

	

	
	
	// POST : Save , Update , Delete
 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Scenario> scenarioes = Arrays.asList(objectMapper.readValue(request.getInputStream(), Scenario[].class));
		
		//Validation 
		for(int i=0;i<scenarioes.size();i++){
			Scenario new_scenario = scenarioes.get(i);
		//reset status
			new_scenario.setScenario_status("");
			new_scenario.setCreated_date(new Date());
			new_scenario.setCreated_user(logged_user);
			new_scenario.setUpdated_date(new Date());
			
			new_scenario.setApplication_no(application_no);
			//stub
			//new_scenario.setPrevious_scenario(scenarioes.get(0));
			
			//
		Status status =service.addNewScenario(new_scenario);
		if(status.getCode()==2){ //NonUniqueKey
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		}
		}
	}
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Scenario> scenarioes = Arrays.asList(objectMapper.readValue(request.getInputStream(), Scenario[].class));
		for(int i=0;i<scenarioes.size();i++){
		//Delete will also be sent as a update request
		
			Scenario scenario = scenarioes.get(i);
			
		if(scenario.getScenario_status().equals("D")){
			//Reset the status
			scenario.setScenario_status("");
			
			service.remove(scenario);
		}else if(scenario.getScenario_status().equals("M")){
			scenario.setScenario_status("");
			scenario.setUpdated_date(new Date());
			service.editScenario(scenario);
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		List<Scenario> scenarioes = Arrays.asList(objectMapper.readValue(request.getInputStream(), Scenario[].class));
		for(int i=0;i<scenarioes.size();i++){
		service.remove(scenarioes.get(i));
		}
		}catch(Exception e){
			System.out.println(e);
		}		
	}
	
	
	//class created for pagination
		private class PagedScenario{
			int total_count;
			List<Scenario> data;
			public int getTotal_count() {
				return total_count;
			}
			public void setTotal_count(int total_count) {
				this.total_count = total_count;
			}
			public List<Scenario> getData() {
				return data;
			}
			public void setData(List<Scenario> data) {
				this.data = data;
			}
		}
		private void exportData(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
			
			String filters_array = request.getParameter("filter");
			List<Filter> filters =null;
			if(filters_array != null){
				filters =  Arrays.asList(objectMapper.readValue(filters_array, Filter[].class));
			}
			
			
			ArrayList<Scenario> scenarioes = (ArrayList<Scenario>) service.getAll(application_no,0,-1,filters);		
			
			
			
			try {
				 XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("items");
				 XSSFTable table = sheet.createTable();
				 CTTable cttable = table.getCTTable();

				 cttable.setDisplayName("Table1");
				 cttable.setId(1);
				 cttable.setName("Table1");
				 //Reference from A1:B[noOfrecords]
				  	 int s =scenarioes.size()+1;
				 String ref = "A1:B"+s;
				 cttable.setRef(ref);
				 cttable.setTotalsRowShown(false);

				/* CTTableStyleInfo styleInfo = cttable.addNewTableStyleInfo();
				 styleInfo.setName("TableStyleMedium2");
				 styleInfo.setShowColumnStripes(false);
				 styleInfo.setShowRowStripes(true);

				 CTTableColumns columns = cttable.addNewTableColumns();
				 columns.setCount(2);
				 
				 CTTableColumn column = columns.addNewTableColumn();
				 column.setId(1);
				 column.setName("scenario_description");
				 
				 CTTableColumn column2 = columns.addNewTableColumn();
				 column2.setId(2);
				 column2.setName("scenario_code");
				 
				 XSSFRow row_header = sheet.createRow(0);
		    	 XSSFCell cell1 = row_header.createCell(0);
		    	 
		    	 XSSFCell cell2 = row_header.createCell(1);
				 cell1.setCellValue("scenario_description"); //content **must** be here for table column names
				 cell2.setCellValue("scenario_code");
			
				 int j = 0;
				 int index = 0;
				for (int i = 1; i < scenarioes.size() + 1; i++) { // as index is
																	// staring from 2
						Scenario sc = scenarioes.get(index);
						XSSFRow row = sheet.createRow(i);
						row.createCell(j++).setCellValue(sc.getScenario_description());
						row.createCell(j++).setCellValue(sc.getScenario_code());
						j = 0;
						index++;
					}

		//---creating a named reference
					XSSFName name = workbook.createName();
					 name.setNameName("test");
					 name.setRefersToFormula("Table1[scenario_description]"); 
					 
*/					 
		//----------------------Sheet containing data-----------------//
				///	 XSSFSheet sheet2 = workbook.createSheet("ScenarioManagement");
					// XSSFTable table2 = sheet2.createTable();
				//	 CTTable cttable2 = table2.getCTTable();

				//	 cttable2.setDisplayName("Table2");
				//	 cttable2.setId(1);
				//	 cttable2.setName("Table2");
					 //Reference from A1:B[noOfrecords]
				//	 int s2 =scenarioes.size()+1;
					// String ref2 = "A2:T"+s2;
					// cttable2.setRef(ref2);
					// cttable2.setTotalsRowShown(false);

					 /*					 CTTableStyleInfo styleInfo2 = cttable2.addNewTableStyleInfo();
					 styleInfo2.setName("TableStyleMedium2");
					 styleInfo2.setShowColumnStripes(false);
					 styleInfo2.setShowRowStripes(true);

					//---------create columns-------------------------//
					 CTTableColumns columns2 = cttable2.addNewTableColumns();
					 columns2.setCount(21);
					 
					 CTTableColumn col = columns2.addNewTableColumn();
					 col.setId(1);
					 col.setName("scenario_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(2);
					 col.setName("scenario_description");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(3);
					 col.setName("version");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(4);
					 col.setName("year");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(5);
					 col.setName("previous_scenario");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(6);
					 col.setName("next_scenario");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(7);
					 col.setName("ref_scenario1");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(8);
					 col.setName("ref_scenario2");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(9);
					 col.setName("ref_scenario3");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(10);
					 col.setName("ref_scenario4");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(11);
					 col.setName("ref_scenario5");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(12);
					 col.setName("ref_scenario6");
					 //hidden columns
					 col = columns2.addNewTableColumn();
					 col.setId(13);
					 col.setName("previous_scenario_code");

					 col = columns2.addNewTableColumn();
					 col.setId(14);
					 col.setName("next_scenario_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(15);
					 col.setName("ref_scenario1_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(16);
					 col.setName("ref_scenario2_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(17);
					 col.setName("ref_scenario3_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(18);
					 col.setName("ref_scenario4_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(19);
					 col.setName("ref_scenario5_code");
					 
					 col = columns2.addNewTableColumn();
					 col.setId(20);
					 col.setName("ref_scenario6_code");

					  //----hidden code header row----------//
					 XSSFRow row_code = sheet2.createRow(0);
					 
					 row_code.createCell(0).setCellValue("scenario_code");
				        row_code.createCell(1).setCellValue("scenario_description");
				        row_code.createCell(2).setCellValue("version");
				        row_code.createCell(3).setCellValue("year");
				        row_code.createCell(4).setCellValue("previous_scenario");
				        row_code.createCell(5).setCellValue("next_scenario");
				        row_code.createCell(6).setCellValue("ref_scenario1");
				        row_code.createCell(7).setCellValue("ref_scenario2");
				        row_code.createCell(8).setCellValue("ref_scenario3");
				        row_code.createCell(9).setCellValue("ref_scenario4");
				        row_code.createCell(10).setCellValue("ref_scenario5");
				        row_code.createCell(11).setCellValue("ref_scenario6");
				        
				        row_code.createCell(12).setCellValue("previous_scenario_code");
				        row_code.createCell(13).setCellValue("next_scenario_code");
				        row_code.createCell(14).setCellValue("ref_scenario1_code");
				        row_code.createCell(15).setCellValue("ref_scenario2_code");
				        row_code.createCell(16).setCellValue("ref_scenario3_code");
				        row_code.createCell(17).setCellValue("ref_scenario4_code");
				        row_code.createCell(18).setCellValue("ref_scenario5_code");
				        row_code.createCell(19).setCellValue("ref_scenario6_code");
						
						CellStyle hiddenstyle = workbook.createCellStyle();
						hiddenstyle.setHidden(true);
						row_code.setRowStyle(hiddenstyle);

						row_code.setZeroHeight(true);
			           //-----------Visible header row--------------//
						row_header = sheet2.createRow(1);
						 row_header.createCell(0).setCellValue("Scenario Code");
					        row_header.createCell(1).setCellValue("Scenario Description");
					        row_header.createCell(2).setCellValue("Version");
					        row_header.createCell(3).setCellValue("Year");
					        row_header.createCell(4).setCellValue("Previous Scenario");
					        row_header.createCell(5).setCellValue("Next Scenario");
					        row_header.createCell(6).setCellValue("Reference Scenario 1");
					        row_header.createCell(7).setCellValue("Reference Scenario 2");
					        row_header.createCell(8).setCellValue("Reference Scenario 3");
					        row_header.createCell(9).setCellValue("Reference Scenario 4");
					        row_header.createCell(10).setCellValue("Reference Scenario 5");
					        row_header.createCell(11).setCellValue("Reference Scenario 6");
					
					        row_header.createCell(12).setCellValue("Previous Scenario Code");
					        row_header.createCell(13).setCellValue("Next Scenario Code");
					        row_header.createCell(14).setCellValue("Reference Scenario 1 Code");
					        row_header.createCell(15).setCellValue("Reference Scenario 2 Code");
					        row_header.createCell(16).setCellValue("Reference Scenario 3 Code");
					        row_header.createCell(17).setCellValue("Reference Scenario 4 Code");
					        row_header.createCell(18).setCellValue("Reference Scenario 5 Code");
					        row_header.createCell(19).setCellValue("Reference Scenario 6 Code");
			  			
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
						   CellRangeAddressList scenarioList = new CellRangeAddressList(2, scenarioes.size()+2, 4, 11);            
						   DataValidation validation = dvHelper.createValidation(dvConstraint, scenarioList);
						   sheet2.addValidationData(validation);
						   
						   
						   
			        	//Add data
			        	j = 0;
						index = 0;
						for (int i = 2; i < scenarioes.size() + 2; i++) { // as index is
																		// staring from 2
							Scenario scenario  = scenarioes.get(index);

							XSSFRow row = sheet2.createRow(i);
					        row.createCell(j++).setCellValue(scenario.getScenario_code());
					        row.createCell(j++).setCellValue(scenario.getScenario_description());
					        row.createCell(j++).setCellValue(scenario.getVersion());
					        row.createCell(j++).setCellValue(scenario.getYear());
					    
					        if(scenario.getPrevious_scenario() !=null){
					        row.createCell(j).setCellValue(scenario.getPrevious_scenario_desc());}
					        j++;
					     
					        if(scenario.getNext_scenario() !=null){
					        row.createCell(j).setCellValue(scenario.getNext_scenario_desc());}
					        j++;
					        
					        if(scenario.getRef_scenario1() !=null){
					        row.createCell(j).setCellValue(scenario.getRef_scenario1_desc());}
					        j++;
					      
					        if(scenario.getRef_scenario2() !=null){
					        row.createCell(j).setCellValue(scenario.getRef_scenario2_desc());}
					        j++;
					      
					        if(scenario.getRef_scenario3() !=null){
					        row.createCell(j).setCellValue(scenario.getRef_scenario3_desc());}
					        j++;
					      
					        if(scenario.getRef_scenario4() !=null){
					        row.createCell(j).setCellValue(scenario.getRef_scenario4_desc());}
					        j++;
					      
					        if(scenario.getRef_scenario5() !=null){
					        row.createCell(j).setCellValue(scenario.getRef_scenario5_desc());}
					        j++;
					      
					        if(scenario.getRef_scenario6() !=null){
					        row.createCell(j).setCellValue(scenario.getRef_scenario6_desc());}
					        j++;
					      
							//Add hidden code values for combo boxes
					        
					        //previous_scenario_code
							XSSFCell c=row.createCell(j++);
							if(scenario.getPrevious_scenario() != null)
							c.setCellValue(scenario.getPrevious_scenario());
							//set each previous scenario code to vlookup value
							int size = scenarioes.size()+1;
							int v = i+1;
							String formula = "IFERROR(VLOOKUP(E"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c.setCellFormula(formula);
							
							//next_scenario_code
							XSSFCell c2=row.createCell(j++);
							if(scenario.getNext_scenario() != null)
							c2.setCellValue(scenario.getNext_scenario());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(F"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c2.setCellFormula(formula);
							
							
							//ref1_scenario_code
							XSSFCell c3=row.createCell(j++);
							if(scenario.getRef_scenario1() != null)
							c3.setCellValue(scenario.getRef_scenario1());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(G"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c3.setCellFormula(formula);
							
							//ref2_scenario_code
							XSSFCell c4=row.createCell(j++);
							if(scenario.getRef_scenario2() != null)
							c4.setCellValue(scenario.getRef_scenario2());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(H"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c4.setCellFormula(formula);
							
							//ref3_scenario_code
							XSSFCell c5=row.createCell(j++);
							if(scenario.getRef_scenario3() != null)
							c5.setCellValue(scenario.getRef_scenario3());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(I"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c5.setCellFormula(formula);
							
							
							//ref4_scenario_code
							XSSFCell c6=row.createCell(j++);
							if(scenario.getRef_scenario4() != null)
							c6.setCellValue(scenario.getRef_scenario4());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(J"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c6.setCellFormula(formula);
							
							
							//ref5_scenario_code
							XSSFCell c7=row.createCell(j++);
							if(scenario.getRef_scenario5() != null)
							c7.setCellValue(scenario.getRef_scenario5());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(K"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c7.setCellFormula(formula);
							
							
							//ref6_scenario_code
							XSSFCell c8=row.createCell(j++);
							if(scenario.getRef_scenario6() != null)
							c8.setCellValue(scenario.getRef_scenario6());
							//set each previous scenario code to vlookup value
							size = scenarioes.size()+1;
							v = i+1;
							formula = "IFERROR(VLOOKUP(L"+v+",items!$A$2:$B$"+size+",2,0),\"\")";
							c8.setCellFormula(formula);
							
							j = 0;
							index++;
						}

						//hide the code columns
						sheet2.setColumnHidden(12, true);
						sheet2.setColumnHidden(13, true);
						sheet2.setColumnHidden(14, true);
						sheet2.setColumnHidden(15, true);
						sheet2.setColumnHidden(16, true);
						sheet2.setColumnHidden(17, true);
						sheet2.setColumnHidden(18, true);
						sheet2.setColumnHidden(19, true);*/
								
				//		workbook.getSheetAt(0).setSelected(false);
					//	workbook.setActiveSheet(1);
						//workbook.setSheetHidden(0, true);
						
						
				    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			        response.setHeader("Content-Disposition", "attachment; filename="+"scenario_management.xlsx");

			        ServletOutputStream out = response.getOutputStream();
			            workbook.write(out);
			            out.flush();
			            out.close();
			       workbook.close();
			       
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		  
			
		}

	
}
		


