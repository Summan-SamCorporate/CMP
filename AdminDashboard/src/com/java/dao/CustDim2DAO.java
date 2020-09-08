package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim2;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim2DAO {
	
	public List<CustDim2> list(String application_no);
	public Status add(CustDim2 custdim);
	public Status update(CustDim2 custdim);
	
	public Status delete(CustDim2 custdim);
	
	public List<CustDim2> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
