package com.java.services;

import java.util.List;
import com.java.dao.TransformationDAO;
import com.java.dao.TransformationDAOImpl;
import com.java.entity.Status;
import com.java.entity.Transformation;
import com.java.pojo.Filter;

public class TransformationServiceImpl implements TransformationService {

	private TransformationDAO transformationDAO = new TransformationDAOImpl();


	@Override
	public List<Transformation> getAll() {
		
		return transformationDAO.list();
	}

	@Override
	public List<Transformation> getAll(String user_name) {
		
		return transformationDAO.list(user_name);
	}

	@Override
	public Status addNewTransformation(Transformation new_container) {
		
		Status status = transformationDAO.add(new_container);
		log.info("New Transformation "+new_container.getTransformation_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editTransformation(Transformation container) {
		
		//Add default values
		log.info("Editting Transformation "+container.getTransformation_code());		
		transformationDAO.update(container);
		return null;
	}

	@Override
	public Status remove(Transformation container) {
		// TODO Auto-generated method stub
		Status status= transformationDAO.delete(container);
		if(status.getCode()==0){
			log.info("Removed Transformation "+container.getTransformation_code());
			}
		return status;
		
	}

	@Override
	public List<Transformation> getAll(String user_name,int start_index, int limit,List<Filter> filters) {

		return transformationDAO.getAll(user_name,start_index,limit,filters);
	}

	@Override
	public int totalCount(String user_name,List<Filter> filters) {
		return transformationDAO.total_records(user_name,filters);
	}

}
