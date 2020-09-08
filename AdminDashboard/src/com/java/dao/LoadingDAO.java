package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Loading;
import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.Users;


public interface LoadingDAO {
	
	public List<Loading> list();
	public List<Loading> list(String user_name);
	
	public Status add(Loading loading);
	public Status update(Loading loading);
	
	public Status delete(Loading loading);
	
	public List<Loading> getAll(String user_name,int start_index, int limit,List<Filter> filter);
	public int total_records(String user_name,List<Filter> filter);
	
	
	}
