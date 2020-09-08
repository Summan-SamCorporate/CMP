package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Status;
import com.java.entity.Users;

/* Service  created to fetch / update / add data in Model table */

public interface UsersService {

	static Logger log = Logger.getLogger(UsersService.class);

	//Get all records from Model table
	public List<Users> getAll();
	public Status addNewUser(Users new_user);
	public Status remove(Users new_user);
	public Status editUser(Users new_user);
	public Users get(String username);
	public List<Users> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	

}
