package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Currency;
import com.java.entity.CustDim10;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface CustDim10Service {

	static Logger log = Logger.getLogger(CustDim10Service.class);

	public List<CustDim10> getAll(String application_no);
	public Status addNewCustDim(CustDim10 new_custDim);
	public Status remove(CustDim10 new_custDim);
	public Status editCustDim(CustDim10 new_custDim);
	public List<CustDim10> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
}
