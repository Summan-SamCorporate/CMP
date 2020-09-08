package com.java.dao;

import java.util.List;

import com.java.entity.Transformation;
import com.java.pojo.Filter;
import com.java.entity.Status;



public interface TransformationDAO {
	
	public List<Transformation> list();
	public List<Transformation> list(String user_name);
	
	public Status add(Transformation transformation);
	public Status update(Transformation transformation);
	
	public Status delete(Transformation transformation);
	
	public List<Transformation> getAll(String user_name,int start_index, int limit,List<Filter> filter);
	public int total_records(String user_name,List<Filter> filter);
	
	
	}
