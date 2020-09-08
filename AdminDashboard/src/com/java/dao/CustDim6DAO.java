package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim6;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim6DAO {
	
	public List<CustDim6> list(String application_no);
	public Status add(CustDim6 custdim);
	public Status update(CustDim6 custdim);
	
	public Status delete(CustDim6 custdim);
	
	public List<CustDim6> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
