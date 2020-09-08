package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Roles;

/* Service  created to fetch / update / add data in Model table */

public interface RolesService {

	static Logger log = Logger.getLogger(RolesService.class);

	//Get all records from Model table
	public List<Roles> getAll();
	public List<Roles> get(); // Get function returns all except superadmin
	public Status addNewRole(Roles roles);
	public Status remove(Roles new_role);
	public Status editRole(Roles new_role);
	public List<Roles> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
}
