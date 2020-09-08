package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.CustDim4;
import com.java.entity.CustDim5;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface CustDim5Service {

	static Logger log = Logger.getLogger(CustDim5Service.class);


	public List<CustDim5> getAll(String application_no);
	public Status addNewCustDim(CustDim5 new_custDim);
	public Status remove(CustDim5 new_custDim);
	public Status editCustDim(CustDim5 new_custDim);
	public List<CustDim5> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
}
