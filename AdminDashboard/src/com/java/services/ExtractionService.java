package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Extraction;


/* Service  created to fetch / update / add data in Application table */

public interface ExtractionService {
	static Logger log = Logger.getLogger(ExtractionService.class);

	public List<Extraction> getAll();
	public List<Extraction> getAll(String user_name);
	
	public Status addNewExtraction(Extraction new_extraction);
	public Status remove(Extraction new_extraction);
	public Status editExtraction(Extraction new_extraction);
	
	public List<Extraction> getAll(String user_name,int start_index, int limit,List<Filter> filters);
	public int totalCount(String user_name,List<Filter> filters);

	
}
