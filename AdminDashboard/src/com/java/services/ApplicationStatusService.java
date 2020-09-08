package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.ApplicationStatus;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface ApplicationStatusService {
	static Logger log = Logger.getLogger(ApplicationStatusService.class);
	
	//Get all records from ApplicationStatus table
	public List<ApplicationStatus> getAll();
	public Status addNewStatus(ApplicationStatus new_status);
	public Status remove(ApplicationStatus new_application);
	public Status editStatus(ApplicationStatus new_status);
	
	public List<ApplicationStatus> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
