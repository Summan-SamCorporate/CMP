package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Label;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface LabelService {
	static Logger log = Logger.getLogger(LabelService.class);
	
	//Get all records from Label table
	public List<Label> getAll();
	public Status addNewLabel(Label new_label);
	public Status remove(Label new_label);
	public Status editLabel(Label new_label);
	public List<Label> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
