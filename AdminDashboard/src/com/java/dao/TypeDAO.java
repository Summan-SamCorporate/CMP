package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.Type;
import com.java.entity.Users;


public interface TypeDAO {
	
	public List<Type> list();
	public Status add(Type type);
	public Status update(Type type);
	
	public Status delete(Type type);
	
	public List<Type> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	
	}
