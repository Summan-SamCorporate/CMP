package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.CustDim1;
import com.java.entity.CustDim12;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface CustDim1Service {

	static Logger log = Logger.getLogger(CustDim1Service.class);


	public List<CustDim1> getAll(String application_no);
	public Status addNewCustDim(CustDim1 new_custDim);
	public Status remove(CustDim1 new_custDim);
	public Status editCustDim(CustDim1 new_custDim);
	public List<CustDim1> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
}
