package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CategoryTypeDAO;
import com.java.dao.CategoryTypeDAOImpl;
import com.java.entity.Status;
import com.java.entity.Category;
import com.java.entity.CategoryType;
import com.java.pojo.Filter;

public class CategoryTypeServiceImpl implements CategoryTypeService {

	private CategoryTypeDAO categorytypeDAO = new CategoryTypeDAOImpl();

	@Override
	public List<CategoryType> getAll() {
		// TODO Auto-generated method stub
		return categorytypeDAO.list();
	}

	@Override
	public Status addNewCategory(CategoryType new_category) {
		
		Status status = categorytypeDAO.add(new_category);
		log.info(new_category.getCategory_type() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCategory(CategoryType category) {
		
		//Add default values
		log.info("Editting CategoryType "+category.getCategory_type());
		
		categorytypeDAO.Update(category);
		return null;
	}

	@Override
	public Status remove(CategoryType category) {
		// TODO Auto-generated method stub
		
		Status status =categorytypeDAO.delete(category);
		if(status.getCode()==0){
		log.info("Removed CategoryType "+category.getCategory_type());
		}
		return status;
	
	}

	@Override
	public List<CategoryType> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return categorytypeDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return categorytypeDAO.total_records(application_no,filters);
	}


}
