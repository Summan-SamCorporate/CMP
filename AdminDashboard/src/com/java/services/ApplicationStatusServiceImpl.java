package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.ApplicationStatusDAO;
import com.java.dao.ApplicationStatusDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.ApplicationStatus;
import com.java.pojo.Filter;

public class ApplicationStatusServiceImpl implements ApplicationStatusService {

	private ApplicationStatusDAO statusDAO = new ApplicationStatusDAOImpl();

	@Override
	public List<ApplicationStatus> getAll() {
		// TODO Auto-generated method stub
		return statusDAO.list();
	}

	@Override
	public Status addNewStatus(ApplicationStatus new_status) {
		
		Status status = statusDAO.add(new_status);
		log.info(new_status.getStatus_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editStatus(ApplicationStatus status) {
		
		//Add default values
		log.info("Editting Application status "+status.getStatus_code());		
		statusDAO.Update(status);
		return null;
	}

	@Override
	public Status remove(ApplicationStatus app_status) {
		// TODO Auto-generated method stub
	
		Status status =statusDAO.delete(app_status);
		if(status.getCode()==0){
		log.info("Removed Application Status "+app_status.getStatus_code());
		}
		return status;
	}

	@Override
	public List<ApplicationStatus> getAll( int start_index, int limit,List<Filter> filters) {

		return statusDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return statusDAO.total_records(filters);
	}

	

}
