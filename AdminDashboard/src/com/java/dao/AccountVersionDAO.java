package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account_Version;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface AccountVersionDAO {
	
	public List<Account_Version> list();
	public Status add(Account_Version version);
	public Status Update(Account_Version version);
	
	public Status delete(Account_Version version);
	
	public List<Account_Version> getAll(int start_index, int limit,List<Filter> filter);
	public int total_records(List<Filter> filter);
	

	}
