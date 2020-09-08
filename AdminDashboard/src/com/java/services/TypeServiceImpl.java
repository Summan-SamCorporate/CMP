package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.TypeDAO;
import com.java.dao.TypeDAOImpl;
import com.java.pojo.Filter;
import com.java.entity.Status;
import com.java.entity.Type;
import com.java.entity.Users;

public class TypeServiceImpl implements TypeService {

	private TypeDAO typeDAO = new TypeDAOImpl();

	@Override
	public List<Type> getAll() {
		// TODO Auto-generated method stub
		return typeDAO.list();
	}

	@Override
	public Status addNewType(Type new_type) {
		
	Status status = typeDAO.add(new_type);
		log.info(new_type.getType_code()+ " Added Successfully");
		
		return status;
	}
	@Override
	public Status editType(Type type) {
		
		//Add default values
		log.info("Editting Type"+type.getType_code());		
		typeDAO.update(type);
		return null;
	}

	@Override
	public Status remove(Type type) {
		// TODO Auto-generated method stub
	
		Status status =typeDAO.delete(type);
		if(status.getCode()==0){
		log.info("Removed Type "+type.getType_code());
		}
		return status;
	}

	@Override
	public List<Type> getAll( int start_index, int limit,List<Filter> filters) {

		return typeDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return typeDAO.total_records(filters);
	}


}
