package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.ApplicationStatus;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface ApplicationStatusDAO {
	
	public List<ApplicationStatus> list();
	public Status add(ApplicationStatus status);
	public Status Update(ApplicationStatus status);
	
	public Status delete(ApplicationStatus status);
	
	public List<ApplicationStatus> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	
	}
