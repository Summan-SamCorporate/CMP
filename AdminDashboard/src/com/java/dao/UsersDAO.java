package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Period;
import com.java.entity.Status;
import com.java.entity.Users;


public interface UsersDAO {
	
	
	static Logger log = Logger.getLogger(UsersDAO.class);
	//public Status updateUser(Users user);
	
	public List<Users> list();
	public Status add(Users user);
	public Users update(Users user);
	
	public Status addUpdateAll(List<Users> users);	
	public Status delete(Users user);

	public Status changePassword(Users user);
	public Users searchUser(String user);
	
	public List<Users> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	

	}
