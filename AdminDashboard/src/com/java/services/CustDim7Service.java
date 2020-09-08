package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.CustDim6;
import com.java.entity.CustDim7;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface CustDim7Service {

	static Logger log = Logger.getLogger(CustDim7Service.class);

	public List<CustDim7> getAll(String application_no);
	public Status addNewCustDim(CustDim7 new_custDim);
	public Status remove(CustDim7 new_custDim);
	public Status editCustDim(CustDim7 new_custDim);
	public List<CustDim7> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);

	
}
