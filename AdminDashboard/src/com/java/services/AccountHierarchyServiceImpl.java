package com.java.services;
/* Created By: Summan Bahadur
 * Description: Interface for Account Hierarchy 
 * Reference/s: Called from Data Modeling - Account Hierarchy
 * 
 * */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.AccountHierarchyDAO;
import com.java.dao.AccountHierarchyDAOImpl;
import com.java.entity.Status;
import com.java.entity.AccountHierarchy;
import com.java.entity.AccountHierarchyMapping;
import com.java.entity.AccountHierarchyStructure;
import com.java.pojo.AccountTreeNode;
import com.java.pojo.Filter;
import com.java.entity.Period;


public class AccountHierarchyServiceImpl implements AccountHierarchyService {

	private AccountHierarchyDAO hierarchyDAO = new AccountHierarchyDAOImpl();

	@Override
	public Status addNewHierarchy(AccountHierarchy new_hierarchy) {
		
		Status status  = hierarchyDAO.add(new_hierarchy);
		log.info(new_hierarchy.getAccount_hierarchy_code() + " Added Successfully");
		
		//Add default root node in account hierarchy structure table
		createDefaultNode(new_hierarchy,new_hierarchy.getApplication_no(),new_hierarchy.getCreated_user());
		
		return status;
	}
	@Override
	public Status editHierarchy(AccountHierarchy hierarchy) {
		
		//Add default values
		log.info("Editting Hierarchy "+hierarchy.getAccount_hierarchy_code());
		hierarchyDAO.Update(hierarchy);
		return null;
	}

	@Override
	public Status remove(AccountHierarchy hierarchy) {
		// TODO Auto-generated method stub
		
		Status status =hierarchyDAO.delete(hierarchy);
		if(status.getCode()==0){
		log.info("Removed Hierarchy "+hierarchy.getAccount_hierarchy_code());
		}
		return status;
	}
	@Override
	public ArrayList<AccountHierarchy> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return (ArrayList<AccountHierarchy>) hierarchyDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return hierarchyDAO.total_records(application_no,filters);
	}
	@Override
	public Status createDefaultNode(AccountHierarchy account_hierarchy, String application_no,String user_name) {
	
		//Default Node is created when a hierarchy object is created.
		AccountHierarchyStructure root = new AccountHierarchyStructure();
		root.setLabel(account_hierarchy.getRoot());
		root.setNode(account_hierarchy.getRoot());
		root.setNode_description(account_hierarchy.getRoot_description());
		root.setNode_type("root");
		root.setParent("0");
		root.setCreated_date(new Date());
		root.setCreated_user(user_name);
		root.setAccount_hierarchy_code(account_hierarchy.getAccount_hierarchy_code());
		root.setApplication_no(application_no);
		root.setColumn_status("");
		hierarchyDAO.create(root);
		
		return null;
	}
	@Override
	public Status addTreeNode(AccountHierarchyStructure node) {
		
		return hierarchyDAO.create(node);
	}
	@Override
	public Status updateTreeNode(AccountHierarchyStructure node) {
		
		return hierarchyDAO.update(node);
	}
	@Override
	public Status deleteNode(AccountHierarchyStructure node) {
		
		return hierarchyDAO.delete(node);
	}
	@Override
	public AccountTreeNode getHierarchyStructure(String account_hierarchy_code) {

		return hierarchyDAO.get(account_hierarchy_code);
	}

	@Override
	public List<AccountHierarchyStructure> getNodesList(String account_hierarchy_code) {

		return hierarchyDAO.getNodesList(account_hierarchy_code);
	}

	//--------------Account Hierarchy Mapping---------------//
	@Override
	public Status addNewMapping(AccountHierarchyMapping new_mapping) {
		
		new_mapping = hierarchyDAO.addUpdate(new_mapping);
		log.info(new_mapping.getMapping_id() + " Added Successfully");
		
		return Status.SUCCESS;
	}
	@Override
	public Status editMapping(AccountHierarchyMapping mapping) {
		
		//Add default values
		log.info("Editting Hierarchy "+mapping.getMapping_id());
		hierarchyDAO.addUpdate(mapping);
		return null;
	}

	@Override
	public Status removeMapping(AccountHierarchyMapping mapping) {
		// TODO Auto-generated method stub
		
		Status status =hierarchyDAO.delete(mapping);
		if(status.getCode()==0){
		log.info("Removed Hierarchy "+mapping.getMapping_id());
		}
		return status;
	}
	@Override
	public ArrayList<AccountHierarchyMapping> getAllMapping(String application_no,String account_hierarchy_code, int start_index, int limit,List<Filter> filters) {

		return (ArrayList<AccountHierarchyMapping>) hierarchyDAO.getAllMapping(application_no,account_hierarchy_code,start_index,limit,filters);
	}
	
	@Override
	public ArrayList<AccountHierarchyMapping> getAllMapping(String node_code) {

		return (ArrayList<AccountHierarchyMapping>) hierarchyDAO.getAllMapping(node_code);
	}


}
