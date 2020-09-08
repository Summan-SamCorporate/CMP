package com.java.dao;

import com.java.entity.Status;
import com.java.entity.Users;

import java.util.ArrayList;
import java.util.List;

import com.java.pojo.Filter;
import com.java.entity.Roles;


public interface RolesDAO {
	
	
	public List<Roles> list();
	public Status add(Roles role);
	public Status update(Roles role);
	
	public Status delete(Roles role);
	public List<Roles> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	
}
