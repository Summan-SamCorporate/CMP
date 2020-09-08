package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Application;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface ApplicationDAO {
	
	public List<Application> list();
	public List<Application> list(String user_name);
	
	public Status add(Application application);
	public Status Update(Application application);
	
	public Status delete(Application application);
	
	public List<Application> getAll(String user_name,int start_index, int limit,List<Filter> filter);
	public int total_records(String user_name,List<Filter> filter);
	
	
	}
