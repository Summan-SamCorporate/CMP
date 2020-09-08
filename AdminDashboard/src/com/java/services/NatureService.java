package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Nature;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface NatureService {
	static Logger log = Logger.getLogger(NatureService.class);
	
	//Get all records from Nature table
	public List<Nature> getAll();
	public Status addNewNature(Nature new_nature);
	public Status remove(Nature new_nature);
	public Status editNature(Nature new_nature);
	public List<Nature> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
