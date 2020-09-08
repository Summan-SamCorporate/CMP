package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Extraction;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface ExtractionDAO {
	
	public List<Extraction> list();
	public List<Extraction> list(String user_name);
	
	public Status add(Extraction extraction);
	public Status update(Extraction extraction);
	
	public Status delete(Extraction extraction);
	
	public List<Extraction> getAll(String user_name,int start_index, int limit,List<Filter> filter);
	public int total_records(String user_name,List<Filter> filter);
	
	
	}
