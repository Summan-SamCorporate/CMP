package com.java.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Dictionary;

/* Service  created to fetch / update / add data in Dictionary table */

public interface DictionaryService {

	static Logger log = Logger.getLogger(DictionaryService.class);

	//Get all records from Dictionary table
	public List<Dictionary> getAll();
	public Status addNewDictionary(Dictionary new_dictionary);
	public Status remove(Dictionary new_dictionary);
	public Status editDictionary(Dictionary new_dictionary);
	public List<Dictionary> getAll(int start_index, int limit,List<Filter> filters);
	public int totalCount(List<Filter> filters);
	
}
