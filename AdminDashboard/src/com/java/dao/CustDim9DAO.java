package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim9;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim9DAO {
	
	public List<CustDim9> list(String application_no);
	public Status add(CustDim9 custdim);
	public Status update(CustDim9 custdim);
	
	public Status delete(CustDim9 custdim);
	
	public List<CustDim9> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
