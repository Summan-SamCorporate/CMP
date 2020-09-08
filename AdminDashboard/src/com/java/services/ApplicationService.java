package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.Application;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in Application table */

public interface ApplicationService {
	static Logger log = Logger.getLogger(ApplicationService.class);
	//Get all records from Application table
	public List<Application> getAll();
	public List<Application> getAll(String user_name);
	
	public Status addNewApplication(Application new_application);
	public Status remove(Application new_application);
	public Status editApplication(Application new_application);
	
	public List<Application> getAll(String user_name,int start_index, int limit,List<Filter> filters);
	public int totalCount(String user_name,List<Filter> filters);

	
}
