package com.java.dao;

import com.java.entity.Status;
import com.java.entity.Users;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pojo.Filter;
import com.java.entity.Model;


public interface ModelDAO {
	
	
	static Logger log = Logger.getLogger(UsersDAO.class);
	public List<Model> list();
	public Status add(Model model);
	
	public Status Update(Model model);
	
	public Status delete(Model model);
	
	public List<Model> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	
}
