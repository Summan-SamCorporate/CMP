package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.Account;
import com.java.pojo.Filter;
import com.java.entity.Period;

/* Service  created to fetch / update / add data in Application table */

public interface AccountService {
	static Logger log = Logger.getLogger(AccountService.class);
	
	
	public Status addNewAccount(Account new_account);
	public Status remove(Account new_account);
	public Status editAccount(Account new_account);
	
	public List<Account> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	
}
