package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Account_Version;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface AccountVersionService {
	static Logger log = Logger.getLogger(AccountVersionService.class);
	
	//Get all records from Version table
	public List<Account_Version> getAll();
	public Status addNewVersion(Account_Version new_version);
	public Status remove(Account_Version new_version);
	public Status editVersion(Account_Version new_version);
	public List<Account_Version> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
	
}
