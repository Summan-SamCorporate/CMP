package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Nature;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface NatureDAO {
	
	public List<Nature> list();
	public Status add(Nature nature);
	public Status update(Nature nature);

	public Status delete(Nature nature);
	
	public List<Nature> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	

	}
