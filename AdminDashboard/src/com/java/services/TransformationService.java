package com.java.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Transformation;


/* Service  created to fetch / update / add data in Application table */

public interface TransformationService {
	static Logger log = Logger.getLogger(TransformationService.class);

	public List<Transformation> getAll();
	public List<Transformation> getAll(String user_name);
	
	public Status addNewTransformation(Transformation new_transformation);
	public Status remove(Transformation new_transformation);
	public Status editTransformation(Transformation new_transformation);
	
	public List<Transformation> getAll(String user_name,int start_index, int limit,List<Filter> filters);
	public int totalCount(String user_name,List<Filter> filters);

	
}
