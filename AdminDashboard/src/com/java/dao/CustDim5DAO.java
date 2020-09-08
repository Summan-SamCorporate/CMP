package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim5;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim5DAO {
	
	public List<CustDim5> list(String application_no);
	public Status add(CustDim5 custdim);
	public Status update(CustDim5 custdim);
	
	public Status delete(CustDim5 custdim);
	
	public List<CustDim5> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
