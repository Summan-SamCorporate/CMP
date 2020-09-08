package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.Category;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CategoryDAO {
	
	public List<Category> list(String application_no);
	public Status add(Category category);
	public Status Update(Category category);
	
	public Status delete(Category category);
	
	public List<Category> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
