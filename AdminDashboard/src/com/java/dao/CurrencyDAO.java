package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.Currency;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CurrencyDAO {
	
	public List<Currency> list(String application_no);
	public Status add(Currency currency);
	public Status Update(Currency currency);
	
	public Status delete(Currency currency);
	
	public List<Currency> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
