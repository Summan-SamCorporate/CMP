package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Label;
import com.java.pojo.Filter;
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;


public interface LabelDAO {
	
	public List<Label> list();
	public Status add(Label label);
	public Status update(Label label);
	
	public Status delete(Label label);
	
	public List<Label> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	

	}
