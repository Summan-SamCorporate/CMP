package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Currency;
import com.java.pojo.Filter;

/* Service  created to fetch / update / add data in ApplicationStatus table */

public interface CurrencyService {

	static Logger log = Logger.getLogger(CurrencyService.class);


	public List<Currency> getAll(String application_no);
	public Status addNewCurrency(Currency new_currency);
	public Status remove(Currency new_currency);
	public Status editCurrency(Currency new_currency);
	public List<Currency> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
	
}
