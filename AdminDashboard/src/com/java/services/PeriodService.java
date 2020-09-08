package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Period;

/* Service  created to fetch / update / add data in Application table */

public interface PeriodService {
	static Logger log = Logger.getLogger(PeriodService.class);
	
	//Get all records from Period table
	public List<Period> getAll(String application_no);
	public Status addNewPeriod(Period new_account);
	public Status remove(Period new_account);
	public Status editPeriod(Period new_account);
	public List<Period> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
}
