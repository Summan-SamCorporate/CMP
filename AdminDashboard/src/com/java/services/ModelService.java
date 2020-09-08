package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Model;

/* Service  created to fetch / update / add data in Model table */

public interface ModelService {

	static Logger log = Logger.getLogger(ModelService.class);

	//Get all records from Model table
	public List<Model> getAll();
	public Status addNewModel(Model new_model);
	public Status remove(Model new_model);
	public Status editModel(Model new_model);
	public List<Model> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
}
