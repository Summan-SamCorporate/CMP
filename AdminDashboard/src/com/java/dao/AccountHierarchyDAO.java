package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.AccountHierarchy;
import com.java.entity.AccountHierarchyMapping;
import com.java.entity.AccountHierarchyStructure;
import com.java.entity.Period;
import com.java.entity.Status;
import com.java.pojo.AccountTreeNode;
import com.java.pojo.Filter;


public interface AccountHierarchyDAO {
	
	public Status add(AccountHierarchy account_hierarchy);
	public AccountHierarchy Update(AccountHierarchy account_hierarchy);
	
	public Status delete(AccountHierarchy account_hierarchy);
	
	public List<AccountHierarchy> getAll(String application_no, int start_index, int limit,List<Filter> filter);	
	public int total_records(String application_no,List<Filter> filter);
	
	
	public AccountTreeNode get(String account_hierarchy_code);
	public List<AccountHierarchyStructure> getNodesList(String account_hierarchy_code);
	public Status create(AccountHierarchyStructure root);
	public Status create(List<AccountHierarchyStructure> tree);
	public Status update(List<AccountHierarchyStructure> tree);
	public Status update(AccountHierarchyStructure node);
	public Status delete(AccountHierarchyStructure node);
	
	
	//----------Account hIerarchy Mapping---//
	public AccountHierarchyMapping addUpdate(AccountHierarchyMapping mapping);
	public Status delete(AccountHierarchyMapping mapping);
	public List<AccountHierarchyMapping> getAllMapping(String application_no,String account_hierarchy_code, int start_index, int limit,List<Filter> filter);	
	public List<AccountHierarchyMapping> getAllMapping(String node_code);	
	
	}
