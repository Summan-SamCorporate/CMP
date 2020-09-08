package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim12;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim12DAO {
	
	public List<CustDim12> list(String application_no);
	public Status add(CustDim12 custdim);
	public Status update(CustDim12 custdim);
	
	public Status delete(CustDim12 custdim);
	
	public List<CustDim12> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
