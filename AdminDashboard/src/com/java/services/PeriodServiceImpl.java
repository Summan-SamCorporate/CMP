package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.PeriodDAO;
import com.java.dao.PeriodDAOImpl;
import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Period;

public class PeriodServiceImpl implements PeriodService {

	private PeriodDAO periodDAO = new PeriodDAOImpl();
	@Override
	public List<Period> getAll(String application_no) {
		// TODO Auto-generated method stub
		return periodDAO.list(application_no);
	}

	@Override
	public Status addNewPeriod(Period new_period) {
		
		Status status =periodDAO.add(new_period);
		log.info(new_period.getPeriod_code() + " Added Successfully");
		return status;
	}
	@Override
	public Status editPeriod(Period period) {
		
		//Add default values
		log.info("Editting Period "+period.getPeriod_code());
		periodDAO.update(period);
		return null;
	}

	@Override
	public Status remove(Period period) {
		// TODO Auto-generated method stub
		
		Status status =periodDAO.delete(period);
		if(status.getCode()==0){
		log.info("Removed Period "+period.getPeriod_code());
		}
		return status;
	}

	@Override
	public List<Period> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return periodDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return periodDAO.total_records(application_no,filters);
	}

	

}
