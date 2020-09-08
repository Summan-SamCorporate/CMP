package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Container;


/* Service  created to fetch / update / add data in Application table */

public interface ContainerService {
	static Logger log = Logger.getLogger(ContainerService.class);

	public List<Container> getAll();
	public List<Container> getAll(String user_name);
	
	public Status addNewContainer(Container new_container);
	public Status remove(Container new_container);
	public Status editContainer(Container new_container);
	
	public List<Container> getAll(String user_name,int start_index, int limit,List<Filter> filters);
	public int totalCount(String user_name,List<Filter> filters);

	
}
