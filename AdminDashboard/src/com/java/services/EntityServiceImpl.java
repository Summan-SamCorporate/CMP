package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.EntityDAO;
import com.java.dao.EntityDAOImpl;
import com.java.entity.Status;
import com.java.entity.Entities;
import com.java.pojo.Filter;
import com.java.entity.Period;

public class EntityServiceImpl implements EntityService {

	private EntityDAO entityDAO = new EntityDAOImpl();
	@Override
	public List<Entities> getAll(String application_no) {
		// TODO Auto-generated method stub
		return entityDAO.list(application_no);
	}

	@Override
	public Status addNewEntity(Entities new_entity) {
		
		//log.info("Adding New Entity");
		Status status = entityDAO.add(new_entity);
		log.info(new_entity.getEntity_code() + " Added Successfully");
		return status;
	}
	@Override
	public Status editEntity(Entities entity) {
		
		//Add default values
		log.info("Editting Entity "+entity.getEntity_code());
		entityDAO.update(entity);
		return null;
	}

	@Override
	public Status remove(Entities entity) {
		// TODO Auto-generated method stub
		
		Status status =entityDAO.delete(entity);
		if(status.getCode()==0){
		log.info("Removed Entity "+entity.getEntity_code());
		}
		return status;
	}
	@Override
	public List<Entities> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return entityDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return entityDAO.total_records(application_no,filters);
	}

	

}
