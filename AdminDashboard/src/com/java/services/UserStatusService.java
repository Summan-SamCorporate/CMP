package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.UserStatus;
import com.java.entity.Users;


public interface UserStatusService {
	static Logger log = Logger.getLogger(UserStatusService.class);
	
	public List<UserStatus> getAll();
	public Status addNewStatus(UserStatus new_status);
	public Status remove(UserStatus new_status);
	public Status editStatus(UserStatus new_status);
	public List<UserStatus> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
