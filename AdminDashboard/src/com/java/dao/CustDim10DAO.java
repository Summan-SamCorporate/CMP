package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim10;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim10DAO {
	
	public List<CustDim10> list(String application_no);
	public Status add(CustDim10 custdim);
	public Status update(CustDim10 custdim);
	
	public Status delete(CustDim10 custdim);
	
	public List<CustDim10> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
