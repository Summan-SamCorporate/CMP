package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.LabelDAO;
import com.java.dao.LabelDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Label;

public class LabelServiceImpl implements LabelService {

	private LabelDAO labelDAO = new LabelDAOImpl();

	@Override
	public List<Label> getAll() {
		// TODO Auto-generated method stub
		return labelDAO.list();
	}

	@Override
	public Status addNewLabel(Label new_label) {
		
		Status status =labelDAO.add(new_label);
		log.info(new_label.getLabel_code()+ " Added Successfully");
		
		return status;
	}
	@Override
	public Status editLabel(Label label) {
		
		//Add default values
		log.info("Editting Label  "+label.getLabel_code());		
		labelDAO.update(label);
		return null;
	}

	@Override
	public Status remove(Label label) {
		// TODO Auto-generated method stub
	
		Status status =labelDAO.delete(label);
		if(status.getCode()==0){
		log.info("Removed Label Status "+label.getLabel_code());
		}
		return status;
	}

	@Override
	public List<Label> getAll( int start_index, int limit,List<Filter> filters) {

		return labelDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return labelDAO.total_records(filters);
	}


}
