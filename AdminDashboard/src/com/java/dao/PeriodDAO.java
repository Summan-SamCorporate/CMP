package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Status;


public interface PeriodDAO {
	
	public List<Period> list(String application_no);
	public Status add(Period period);
	public Status update(Period period);
	
	public Status delete(Period period);
	
	public List<Period> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	public int total_records(String application_no,List<Filter> filter);
	}
