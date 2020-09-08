package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.ModelDAO;
import com.java.dao.ModelDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Model;

public class ModelServiceImpl implements ModelService {

	private ModelDAO modelDAO = new ModelDAOImpl();

	@Override
	public List<Model> getAll() {
		// TODO Auto-generated method stub
		return modelDAO.list();
	}

	@Override
	public Status addNewModel(Model new_model) {
		
		//Add default values
		new_model.setCreated_date(new Date());
		new_model.setUpdated_date(new Date());
		Status status = modelDAO.add(new_model);
		log.info(new_model.getModel_code()+" Added Successfully");
		
		return status;
	}
	@Override
	public Status editModel(Model model) {
		
		//Add default values
		log.info("Editting Model "+model.getModel_code());
		
		model.setUpdated_date(new Date());
		modelDAO.Update(model);
		return null;
	}

	@Override
	public Status remove(Model new_model) {
		// TODO Auto-generated method stub
		Status status =modelDAO.delete(new_model);
		if(status.getCode()==0){
		log.info("Removed Model "+new_model.getModel_code());
		}
		return status;
	
	}

	@Override
	public List<Model> getAll( int start_index, int limit,List<Filter> filters) {

		return modelDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return modelDAO.total_records(filters);
	}

}
