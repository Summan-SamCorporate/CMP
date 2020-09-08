package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Entities;
import com.java.pojo.Filter;
import com.java.entity.Period;

/* Service  created to fetch / update / add data in Application table */

public interface EntityService {
	static Logger log = Logger.getLogger(EntityService.class);
	
	//Get all records from Application table
	public List<Entities> getAll(String application_no);
	public Status addNewEntity(Entities new_entity);
	public Status remove(Entities new_entity);
	public Status editEntity(Entities new_entity);
	public List<Entities> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
}
