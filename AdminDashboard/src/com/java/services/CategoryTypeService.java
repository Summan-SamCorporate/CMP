package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.CategoryType;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in CategoryType table */

public interface CategoryTypeService {

	static Logger log = Logger.getLogger(CategoryTypeService.class);

	//Get all records from CategoryType table
	public List<CategoryType> getAll();
	public Status addNewCategory(CategoryType new_category);
	public Status remove(CategoryType new_category);
	public Status editCategory(CategoryType new_category);
	public List<CategoryType> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
	
}
