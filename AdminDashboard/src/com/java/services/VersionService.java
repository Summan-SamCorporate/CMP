package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Scenario_Version;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface VersionService {
	static Logger log = Logger.getLogger(VersionService.class);
	
	//Get all records from Version table
	public List<Scenario_Version> getAll();
	public Status addNewVersion(Scenario_Version new_nature);
	public Status remove(Scenario_Version new_nature);
	public Status editVersion(Scenario_Version new_nature);
	public List<Scenario_Version> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
