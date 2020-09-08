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

import com.java.entity.Status;
import com.java.entity.AccountHierarchy;
import com.java.entity.AccountHierarchyMapping;
import com.java.entity.AccountHierarchyStructure;
import com.java.pojo.AccountTreeNode;
import com.java.pojo.Filter;
import com.java.entity.Period;


public interface AccountHierarchyService {
	static Logger log = Logger.getLogger(AccountHierarchyService.class);
	
	
	public Status addNewHierarchy(AccountHierarchy new_hierarchy);
	public Status remove(AccountHierarchy hierarchy);
	public Status editHierarchy(AccountHierarchy hierarchy);
	public List<AccountHierarchy> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	//Account Hierarchy Structure
	public Status createDefaultNode(AccountHierarchy account_hierarchy, String application_no,String user_created);
	public Status addTreeNode(AccountHierarchyStructure node);
	public Status updateTreeNode(AccountHierarchyStructure node);
	public Status deleteNode(AccountHierarchyStructure node);
	public AccountTreeNode getHierarchyStructure(String account_hierarchy_code);
	//To display all nodes in drop down for selection
	public List<AccountHierarchyStructure> getNodesList(String account_hierarchy_code);
	
	//Account Hierarchy Structure Mapping
	public Status addNewMapping(AccountHierarchyMapping new_hierarchy_mapping);
	public Status removeMapping(AccountHierarchyMapping hierarchy_mapping);
	public Status editMapping(AccountHierarchyMapping hierarchy_mapping);
	public List<AccountHierarchyMapping> getAllMapping(String application_no,String account_hierarchy_code, int start_index, int limit,List<Filter> filters);
	public List<AccountHierarchyMapping> getAllMapping(String node_code);
	//public int totalCount(String application_no,List<Filter> filters);
	
}
