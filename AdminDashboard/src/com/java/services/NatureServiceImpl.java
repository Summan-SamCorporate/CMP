package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.NatureDAO;
import com.java.dao.NatureDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Nature;

public class NatureServiceImpl implements NatureService {

	private NatureDAO natureDAO = new NatureDAOImpl();

	@Override
	public List<Nature> getAll() {
		// TODO Auto-generated method stub
		return natureDAO.list();
	}

	@Override
	public Status addNewNature(Nature new_nature) {
		
		Status status = natureDAO.add(new_nature);
		log.info(new_nature.getNature_code()+ " Added Successfully");
		
		return status;
	}
	@Override
	public Status editNature(Nature nature) {
		
		//Add default values
		log.info("Editting Nature  "+nature.getNature_code());		
		natureDAO.update(nature);
		return null;
	}

	@Override
	public Status remove(Nature nature) {
		// TODO Auto-generated method stub
	
		Status status =natureDAO.delete(nature);
		if(status.getCode()==0){
		log.info("Removed Nature Status "+nature.getNature_code());
		}
		return status;
	}

	@Override
	public List<Nature> getAll( int start_index, int limit,List<Filter> filters) {

		return natureDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return natureDAO.total_records(filters);
	}


}
