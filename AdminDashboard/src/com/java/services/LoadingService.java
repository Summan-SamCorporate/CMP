package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Loading;


/* Service  created to fetch / update / add data in Application table */

public interface LoadingService {
	static Logger log = Logger.getLogger(LoadingService.class);

	public List<Loading> getAll();
	public List<Loading> getAll(String user_name);
	
	public Status addNewLoading(Loading new_loading);
	public Status remove(Loading new_loading);
	public Status editLoading(Loading new_loading);
	
	public List<Loading> getAll(String user_name,int start_index, int limit,List<Filter> filters);
	public int totalCount(String user_name,List<Filter> filters);

	
}
