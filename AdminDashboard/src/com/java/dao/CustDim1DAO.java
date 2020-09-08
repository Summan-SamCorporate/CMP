package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim1;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim1DAO {
	
	public List<CustDim1> list(String application_no);
	public Status add(CustDim1 custdim);
	
	public Status update(CustDim1 custdim);
	
	public Status delete(CustDim1 custdim);
	
	public List<CustDim1> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
