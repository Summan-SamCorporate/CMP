package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.Category;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface CategoryService {

	static Logger log = Logger.getLogger(CategoryService.class);

	public List<Category> getAll(String application_no);
	public Status addNewCategory(Category new_category);
	public Status remove(Category new_category);
	public Status editCategory(Category new_category);
	
	public List<Category> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
}
