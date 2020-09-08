package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CategoryType;
import com.java.pojo.Filter;
import com.java.entity.Status;



public interface CategoryTypeDAO {
	
	public List<CategoryType> list();
	public Status add(CategoryType category_type);
	public Status Update(CategoryType category_type);

	public Status delete(CategoryType category_type);
	
	public List<CategoryType> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
