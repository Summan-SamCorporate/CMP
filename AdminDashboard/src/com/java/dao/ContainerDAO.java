package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Container;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface ContainerDAO {
	
	public List<Container> list();
	public List<Container> list(String user_name);
	
	public Status add(Container container);
	public Status Update(Container container);
	
	public Status delete(Container container);
	
	public List<Container> getAll(String user_name,int start_index, int limit,List<Filter> filter);
	public int total_records(String user_name,List<Filter> filter);
	
	
	}
