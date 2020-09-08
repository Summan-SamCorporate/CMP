package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CurrencyDAO;
import com.java.dao.CurrencyDAOImpl;
import com.java.entity.Status;
import com.java.entity.CategoryType;
import com.java.entity.Currency;
import com.java.pojo.Filter;

public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyDAO currencyDAO = new CurrencyDAOImpl();

	@Override
	public List<Currency> getAll(String application_no) {
		// TODO Auto-generated method stub
		return currencyDAO.list(application_no);
	}

	@Override
	public Status addNewCurrency(Currency new_currency) {
		
		Status status = currencyDAO.add(new_currency);
		log.info(new_currency.getCurrency_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCurrency(Currency new_currency) {
		
		//Add default values
		log.info("Editting Currency "+new_currency.getCurrency_code());
		
		currencyDAO.Update(new_currency);
		return null;
	}

	@Override
	public Status remove(Currency new_currency) {
		// TODO Auto-generated method stub
		
		Status status =currencyDAO.delete(new_currency);
		if(status.getCode()==0){
		log.info("Removed Currency "+new_currency.getCurrency_code());
		}
		return status;
	}

	@Override
	public List<Currency> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return currencyDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return currencyDAO.total_records(application_no,filters);
	}
	

}
