package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.Type;
import com.java.entity.Users;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface TypeService {
	static Logger log = Logger.getLogger(TypeService.class);
	
	//Get all records from Type table
	public List<Type> getAll();
	public Status addNewType(Type new_type);
	public Status remove(Type type);
	public Status editType(Type type);
	public List<Type> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
