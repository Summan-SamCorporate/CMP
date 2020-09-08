package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Scenario_Version;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface VersionDAO {
	
	public List<Scenario_Version> list();
	public Status add(Scenario_Version version);
	public Status update(Scenario_Version version);
	
	public Status delete(Scenario_Version version);
	
	public List<Scenario_Version> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	

	}
