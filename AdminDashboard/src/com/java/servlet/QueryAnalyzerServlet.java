package com.java.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.java.services.QueryAnalyzerService;
import com.java.services.QueryAnalyzerServiceImpl;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Called from query analyzer frame
 * Takes in query parameters and creats a query string, sends it to service class for furture execution
 * Service class returns data that is sent back to server in response.
 */
@WebServlet("/QueryAnalyzerServlet")
public class QueryAnalyzerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private QueryAnalyzerService service = new QueryAnalyzerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAnalyzerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String func = request.getParameter("func");
		if (func.equals("get")) {
		
		//get parameters from screen
		String table_name = request.getParameter("table_name");
		String table_column_name = request.getParameter("table_column_name");
		String where_condition = request.getParameter("where_condition");
		String column_name = request.getParameter("column_name");
		String operator = request.getParameter("operator");
		String text = request.getParameter("text");
		String advance_query = request.getParameter("advance_query");
		
		
		int table_column_count = table_column_name.split(",").length;
		
		
		//validation
		String err_message = "";
		//execution
		List<Object> rows = null;
		if (where_condition.equals("false")) {
			if (advance_query.equals("")) {
				rows = service.executeQueryString(table_name, table_column_name);
			} else {
				if (advance_query.toLowerCase().contains("update") || advance_query.toLowerCase().contains("delete")) {
					err_message = "Cannot Update or Delete any Table";
				} else {
					rows = service.executeQueryString(table_name, table_column_name, advance_query);
					if (rows == null) {
						err_message = "SQL Grammer Exception!";
					}
				}
			}
		}
		else{
			rows= service.executeQueryString(table_name,table_column_name,column_name,operator,text);
		}
		

		
		//-----sending response on screen-----------------//
		PrintWriter out = response.getWriter();
		
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
 
        JsonObject myObj = new JsonObject();
 
        if(rows == null){
           myObj.addProperty("success", false);
           myObj.addProperty("errors", err_message);
        }
        else{
        	myObj.addProperty("success", true);
       	   ObjectMapper mapper = new ObjectMapper();
        		// Java object to JSON string
        		String jsonString = mapper.writeValueAsString(rows);
        	   myObj.addProperty("data", jsonString);
        }
         
        out.println(myObj.toString());
       
        out.close();
        //------------------------
		}else if(func.equals("export_data")){
			exportData(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void exportData(HttpServletRequest request, HttpServletResponse response) {
		
		String data =request.getParameter("data");
		String table_name =request.getParameter("table_name");
		String columns_names =request.getParameter("columns_names");
		
		 try {
			JSONObject obj = new JSONObject(data);

            JSONArray docs = obj.getJSONArray("data");
            
         // File file=new File();
            String csv = CDL.toString(docs);
            
            String fileName = table_name;
            String contentType = null;

                contentType = "application/vnd.ms-excel";
            

            byte[] file = csv.getBytes();
            response.setHeader("Content-disposition", "attachment;filename=" + fileName+".xls");
            response.setHeader("charset", "iso-8859-1");
            response.setContentType(contentType);
            response.setContentLength(file.length);
            response.setStatus(HttpServletResponse.SC_OK);

            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                outputStream.write(file, 0, file.length);
                outputStream.flush();
                outputStream.close();
                response.flushBuffer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
