package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.LoadingDAO;
import com.java.dao.LoadingDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.Loading;
import com.java.pojo.Filter;

public class LoadingServiceImpl implements LoadingService {

	private LoadingDAO loadingDAO = new LoadingDAOImpl();


	@Override
	public List<Loading> getAll() {
		
		return loadingDAO.list();
	}

	@Override
	public List<Loading> getAll(String user_name) {
		
		return loadingDAO.list(user_name);
	}

	@Override
	public Status addNewLoading(Loading new_loading) {
		
		Status status = loadingDAO.add(new_loading);
		log.info("New Loading "+new_loading.getLoading_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editLoading(Loading loading) {
		
		//Add default values
		log.info("Editting Loading "+loading.getLoading_code());		
		loadingDAO.update(loading);
		return null;
	}

	@Override
	public Status remove(Loading loading) {
		// TODO Auto-generated method stub
		Status status= loadingDAO.delete(loading);
		if(status.getCode()==0){
			log.info("Removed Loading "+loading.getLoading_code());
			}
		return status;
		
	}

	@Override
	public List<Loading> getAll(String user_name,int start_index, int limit,List<Filter> filters) {

		return loadingDAO.getAll(user_name,start_index,limit,filters);
	}

	@Override
	public int totalCount(String user_name,List<Filter> filters) {
		return loadingDAO.total_records(user_name,filters);
	}

}
