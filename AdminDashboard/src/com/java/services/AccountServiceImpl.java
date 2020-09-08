package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.AccountDAO;
import com.java.dao.AccountDAOImpl;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.pojo.Filter;
import com.java.entity.Period;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDAO = new AccountDAOImpl();

	@Override
	public Status addNewAccount(Account new_account) {
		
		accountDAO.add(new_account);
		log.info(new_account.getAccount_code() + " Added Successfully");
		return Status.SUCCESS;
	}
	@Override
	public Status editAccount(Account account) {
		
		//Add default values
		log.info("Editting Account "+account.getAccount_code());
		accountDAO.Update(account);
		return null;
	}

	@Override
	public Status remove(Account account) {
		// TODO Auto-generated method stub
		
		Status status =accountDAO.delete(account);
		if(status.getCode()==0){
		log.info("Removed Account "+account.getAccount_code());
		}
		return status;
	}
	@Override
	public ArrayList<Account> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return (ArrayList<Account>) accountDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return accountDAO.total_records(application_no,filters);
	}

	

}
