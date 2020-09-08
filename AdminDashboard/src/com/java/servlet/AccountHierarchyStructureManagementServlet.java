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
import com.java.entity.AccountHierarchyStructure;
import com.java.entity.Status;
import com.java.pojo.AccountTreeNode;
import com.java.pojo.Filter;
import com.java.pojo.TreeNode;

/**
 * Servlet implementation class InputRawBas
 */
public class AccountHierarchyStructureManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service variable

	AccountHierarchyService service = new AccountHierarchyServiceImpl();
	
	// Object Mapper to map JSON object
	ObjectMapper objectMapper = new ObjectMapper();

	// Status to return
	Status status = Status.SUCCESS;
	String logged_user;
	String application_no = "";

	public AccountHierarchyStructureManagementServlet() {

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
			else if (func.equals("get_nodes_list")) {
				getNodesList(request, response);

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

		String account_hierarchy_code = request.getParameter("account_hierarchy_code");
		if(account_hierarchy_code != null){
		//ArrayList<AccountHierarchyStructure> hierarchy =  (ArrayList<AccountHierarchyStructure>) service.getHierarchyStructure(account_hierarchy_code);
			AccountTreeNode root= service.getHierarchyStructure(account_hierarchy_code);	
			if(root !=null){ 
		        response.setContentType("application/json");            
		        response.setCharacterEncoding("UTF-8");
		        
		        //Convert flat structure to a tree structure to display on screen
		        //Root node is not getting displayed
		        AccountTreeNode dummy_root = new AccountTreeNode();
		        dummy_root.setText("root");
				dummy_root.setLeaf(false);
				dummy_root.setExpanded(true);
				
				dummy_root.getChildren().add(root);

		        objectMapper.writeValue(response.getOutputStream(), dummy_root);
			  	}
		}

	}
	private void getNodesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String account_hierarchy_code = request.getParameter("account_hierarchy_code");
		if(account_hierarchy_code != null){
		ArrayList<AccountHierarchyStructure> list =  (ArrayList<AccountHierarchyStructure>) service.getNodesList(account_hierarchy_code);
			if(list !=null){ 
		        response.setContentType("application/json");            
		        response.setCharacterEncoding("UTF-8");
		        

		        objectMapper.writeValue(response.getOutputStream(), list);
			  	}
		}

	}
	// POST : Save , Update , Delete

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Structure sent from server will be of type Tree
		List<AccountHierarchyStructure> tree = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchyStructure[].class));

		//Convert data to object data
		for(int i=0; i<tree.size(); i++){
			AccountHierarchyStructure node = tree.get(i);
			
			//Translate Object
			if(node.getColumn_status() != null){
			if(node.getColumn_status().equals("A")){
				node.setColumn_status("");
				node.setApplication_no(application_no);
				node.setCreated_user(logged_user);
				node.setCreated_date(new Date());
				Status status = service.addTreeNode(node);
				if(status.getCode()==2){ //NonUniqueKey
					response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			
				}
			}
			else if (node.getColumn_status().equals("M")){
				node.setColumn_status("");
				node.setUpdated_date(new Date());
				service.updateTreeNode(node);
			}
			else if(node.getColumn_status().equals("D")){
				service.deleteNode(node);
			}
			}
			
			
		}
		
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<AccountHierarchyStructure> hierarchys = Arrays.asList(objectMapper.readValue(request.getInputStream(), AccountHierarchyStructure[].class));

		for (int i = 0; i < hierarchys.size(); i++) {
			// Delete will also be sent as a update request
			AccountHierarchyStructure hierarchy = hierarchys.get(i);

			if (hierarchy.getColumn_status().equals("A")) {
				
				hierarchy.setColumn_status("");
				hierarchy.setApplication_no(application_no);
				hierarchy.setCreated_user(logged_user);
				hierarchy.setCreated_date(new Date());
				service.addTreeNode(hierarchy);
			}
			else if (hierarchy.getColumn_status().equals("D")) {
				// Reset the status
				hierarchy.setColumn_status("");


			} else if (hierarchy.getColumn_status().equals("M")) {
				hierarchy.setColumn_status("");
				hierarchy.setUpdated_date(new Date());
				service.updateTreeNode(hierarchy);
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
