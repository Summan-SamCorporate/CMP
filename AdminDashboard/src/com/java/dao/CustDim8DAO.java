package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim8;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim8DAO {
	
	public List<CustDim8> list(String application_no);
	public Status add(CustDim8 custdim);
	public Status update(CustDim8 custdim);
	
	public Status delete(CustDim8 custdim);
	
	public List<CustDim8> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}