package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Entities;
import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Status;


public interface EntityDAO {
	
	public List<Entities> list(String application_no);
	public Status add(Entities entity);
	public Status update(Entities entity);
	
	public Status delete(Entities entity);
	
	public List<Entities> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
