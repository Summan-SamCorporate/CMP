package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.UserStatusDAO;
import com.java.dao.UserStatusDAOImpl;
import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.UserStatus;
import com.java.entity.Users;

public class UserStatusServiceImpl implements UserStatusService {

	private UserStatusDAO statusDAO = new UserStatusDAOImpl();

	@Override
	public List<UserStatus> getAll() {
		return statusDAO.list();
	}

	@Override
	public Status addNewStatus(UserStatus new_status) {
		
		Status status = statusDAO.add(new_status);
		log.info(new_status.getStatus_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editStatus(UserStatus status) {
		
		//Add default values
		log.info("Editting User status "+status.getStatus_code());		
		statusDAO.update(status);
		return null;
	}

	@Override
	public Status remove(UserStatus app_status) {
		Status status =statusDAO.delete(app_status);
		if(status.getCode()==0){
		log.info("Removed Application Status "+app_status.getStatus_code());
		}
		return status;
	}

	@Override
	public List<UserStatus> getAll( int start_index, int limit,List<Filter> filters) {

		return statusDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return statusDAO.total_records(filters);
	}
	

}
