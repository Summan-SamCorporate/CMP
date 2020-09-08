package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.UserStatus;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.services.AccountService;


public interface UserStatusDAO {
	
	static Logger log = Logger.getLogger(UserStatusDAO.class);
	
	public List<UserStatus> list();
	public Status add(UserStatus status);
	public Status update(UserStatus status);
	
	public Status delete(UserStatus status);
	
	public List<UserStatus> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	
	}
