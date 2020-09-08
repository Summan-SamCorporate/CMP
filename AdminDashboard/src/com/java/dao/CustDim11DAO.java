package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Account;
import com.java.entity.CustDim11;
import com.java.pojo.Filter;
import com.java.entity.Status;


public interface CustDim11DAO {
	
	public List<CustDim11> list(String application_no);
	public Status add(CustDim11 custdim);
	public Status update(CustDim11 custdim);
	
	public Status delete(CustDim11 custdim);
	public List<CustDim11> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	
	}
