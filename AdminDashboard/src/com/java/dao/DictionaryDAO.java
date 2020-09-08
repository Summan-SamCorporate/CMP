package com.java.dao;

import com.java.entity.Status;
import com.java.entity.Users;

import java.util.ArrayList;
import java.util.List;

import com.java.pojo.Filter;
import com.java.entity.Dictionary;


public interface DictionaryDAO {
	
	
	public List<Dictionary> list();
	public Status add(Dictionary dictionary);
	public Status update(Dictionary dictionary);
	
	public Status delete(Dictionary dictionary);
	
	public List<Dictionary> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	
}
