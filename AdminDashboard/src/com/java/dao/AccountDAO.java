package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.Period;
import com.java.entity.Status;
import com.java.pojo.Filter;


public interface AccountDAO {
	
	public Status add(Account account);
	public Status Update(Account account);

	public Status delete(Account account);
	
	public List<Account> getAll(String application_no, int start_index, int limit,List<Filter> filter);	
	public int total_records(String application_no,List<Filter> filter);
	
	}
