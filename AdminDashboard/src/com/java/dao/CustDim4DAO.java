package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim4;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim4DAO {
	
	public List<CustDim4> list(String application_no);
	public Status add(CustDim4 custdim);
	public Status update(CustDim4 custdim);
	
	public Status delete(CustDim4 custdim);
	
	public List<CustDim4> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
