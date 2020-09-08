package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim3;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim3DAO {
	
	public List<CustDim3> list(String application_no);
	public Status add(CustDim3 custdim);
	public Status update(CustDim3 custdim);

	public Status delete(CustDim3 custdim);
	
	public List<CustDim3> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
