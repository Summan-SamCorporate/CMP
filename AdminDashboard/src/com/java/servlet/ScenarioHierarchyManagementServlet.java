package com.java.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
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

import com.java.services.ScenarioService;
import com.java.services.ScenarioServiceImpl;
import com.java.entity.Status;
import com.java.entity.Type;
import com.java.pojo.TreeNode;
import com.java.pojo.user;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.entity.Account;
import com.java.entity.Period;
import com.java.entity.Scenario;
import com.java.entity.Scenario_Tree_Node;

/**
 * Servlet implementation class InputRawBas
 */
public class ScenarioHierarchyManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Service variable

	ScenarioService service = new ScenarioServiceImpl();
	//Object Mapper to map JSON object to Scenario class object
	ObjectMapper objectMapper = new ObjectMapper();
	
	//Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no ="";
	
    public ScenarioHierarchyManagementServlet() {
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
			System.out.println(func);
			if (func.equals("getTree")) {
				getTree(request, response);

			}	
			else if(func.equals("getScenarioes")){
				getScenarioes(request, response);
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
			
		
			String data = (String) request.getParameter("data");
			
			//save in json file
		/*	File file = null;
			try{
				
			
			file = new File("scenario_hierarchy_tree.json");
			System.out.println("Writting to file: "+file.getAbsolutePath());
			file.write(data);
		
			}
			catch(IOException e){
				System.out.println(e);
			}
			finally{
				file.close();
			}*/
			}
	}
	
	private int updateTree(TreeNode tree_node){
		
		if(tree_node.getChildren().size() == 0){
			return 0;
		}
		else{
			for(int i=0; i<tree_node.getChildren().size(); i++){
				
				TreeNode cn = tree_node.getChildren().get(i);
				Scenario_Tree_Node current_node = new Scenario_Tree_Node();
				current_node.setText(cn.getText()); //assuming text is the primary key //TODO: place a hidden id to recognize record
				
				int status =service.setNode(current_node);
				if (status==0){
					//return success
				}
			}
		}
		
		
		
		return -1;
	}

	
	private void getTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Scenario_Tree_Node root = service.getNode("root");
		TreeNode tree_node = new TreeNode();

		if (root != null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			tree_node.setText(root.getText());
			tree_node.setLeaf(false);
			tree_node.setExpanded(true);
			tree_node = traverse(root, tree_node);

		}
		objectMapper.writeValue(response.getOutputStream(), tree_node);

	}

	TreeNode traverse(Scenario_Tree_Node tree_node, TreeNode node) {

		if (tree_node.getNext_node() != null) {
			String[] child_node_codes = tree_node.getNext_node().split(",");
			
			for (int i = 0; i < child_node_codes.length; i++) {
				Scenario_Tree_Node curr_node = service.getNode(child_node_codes[i]);
				TreeNode tn = new TreeNode();
				tn.setText(curr_node.getText());

				TreeNode rn = traverse(curr_node, tn);

				node.getChildren().add(rn);
			}

		}
		else{
			// Add leaf node if exists
			List<Scenario> leafs = service.getLeafScenarioes(tree_node.getNode_code());
        	//create leaf nodes
        	for(int l=0;l<leafs.size();l++){
        		Scenario scenario_leaf = leafs.get(l);
        		TreeNode leaf_tree_node = new TreeNode();
        		leaf_tree_node.setText(scenario_leaf.getScenario_code());
        		leaf_tree_node.setLeaf(true);
        		
        		node.getChildren().add(leaf_tree_node);
        	}
		}

		return node;
	}
	
	private void getScenarioes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Scenario> list = service.getAll(application_no);
		
		TreeNode root = new TreeNode();
		root.setText("root");
	
		for(int i=0; i<list.size(); i++){
			Scenario scenario =  list.get(i);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			TreeNode leaf_tree_node = new TreeNode();
    		leaf_tree_node.setText(scenario.getScenario_code());
    		leaf_tree_node.setLeaf(true);
    		
    		root.getChildren().add(leaf_tree_node);
		}
		
		objectMapper.writeValue(response.getOutputStream(), root);

	}
		


}	

		


