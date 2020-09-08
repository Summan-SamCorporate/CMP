package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim7;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim7DAO {
	
	public List<CustDim7> list(String application_no);
	public Status add(CustDim7 custdim);
	public Status update(CustDim7 custdim);

	public Status delete(CustDim7 custdim);
	
	public List<CustDim7> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
