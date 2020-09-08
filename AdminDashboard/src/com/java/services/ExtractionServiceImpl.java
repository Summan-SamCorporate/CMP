package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.ExtractionDAO;
import com.java.dao.ExtractionDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.Extraction;
import com.java.pojo.Filter;

public class ExtractionServiceImpl implements ExtractionService {

	private ExtractionDAO extractionDAO = new ExtractionDAOImpl();


	@Override
	public List<Extraction> getAll() {
		
		return extractionDAO.list();
	}

	@Override
	public List<Extraction> getAll(String user_name) {
		
		return extractionDAO.list(user_name);
	}

	@Override
	public Status addNewExtraction(Extraction new_container) {
		
		Status status = extractionDAO.add(new_container);
		log.info("New Extraction "+new_container.getExtraction_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editExtraction(Extraction container) {
		
		//Add default values
		log.info("Editting Extraction "+container.getExtraction_code());		
		extractionDAO.update(container);
		return null;
	}

	@Override
	public Status remove(Extraction container) {
		// TODO Auto-generated method stub
		Status status= extractionDAO.delete(container);
		if(status.getCode()==0){
			log.info("Removed Extraction "+container.getExtraction_code());
			}
		return status;
		
	}

	@Override
	public List<Extraction> getAll(String user_name,int start_index, int limit,List<Filter> filters) {

		return extractionDAO.getAll(user_name,start_index,limit,filters);
	}

	@Override
	public int totalCount(String user_name,List<Filter> filters) {
		return extractionDAO.total_records(user_name,filters);
	}

}
