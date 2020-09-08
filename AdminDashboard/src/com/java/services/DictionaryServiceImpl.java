package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.DictionaryDAO;
import com.java.dao.DictionaryDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Dictionary;

public class DictionaryServiceImpl implements DictionaryService {

	private DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

	@Override
	public List<Dictionary> getAll() {
		// TODO Auto-generated method stub
		return dictionaryDAO.list();
	}

	@Override
	public Status addNewDictionary(Dictionary new_dictionary) {
		
		//Add default values
		new_dictionary.setCreated_date(new Date());
		new_dictionary.setUpdated_date(new Date());
		Status status = dictionaryDAO.add(new_dictionary);
		log.info(new_dictionary.getDictionary_code()+" Added Successfully");
		
		return status;
	}
	@Override
	public Status editDictionary(Dictionary dictionary) {
		
		//Add default values
		log.info("Editting Dictionary "+dictionary.getDictionary_code());
		
		dictionary.setUpdated_date(new Date());
		dictionaryDAO.update(dictionary);
		return null;
	}

	@Override
	public Status remove(Dictionary new_dictionary) {
		// TODO Auto-generated method stub
		Status status =dictionaryDAO.delete(new_dictionary);
		if(status.getCode()==0){
		log.info("Removed Dictionary "+new_dictionary.getDictionary_code());
		}
		return status;
	
	}

	@Override
	public List<Dictionary> getAll( int start_index, int limit,List<Filter> filters) {

		return dictionaryDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return dictionaryDAO.total_records(filters);
	}

}
