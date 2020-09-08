package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CategoryDAO;
import com.java.dao.CategoryDAOImpl;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.Category;
import com.java.pojo.Filter;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDAO categoryDAO = new CategoryDAOImpl();

	@Override
	public List<Category> getAll(String application_no) {
		// TODO Auto-generated method stub
		return categoryDAO.list(application_no);
	}

	@Override
	public Status addNewCategory(Category new_category) {
		
		Status status =categoryDAO.add(new_category);
		log.info(new_category.getCategory_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCategory(Category new_category) {
		
		//Add default values
		log.info("Editting Category "+new_category.getCategory_code());
		
		categoryDAO.Update(new_category);
		return null;
	}

	@Override
	public Status remove(Category new_category) {
		// TODO Auto-generated method stus
		Status status =categoryDAO.delete(new_category);
		if(status.getCode()==0){
		log.info("Removed Category "+new_category.getCategory_code());
		}
		return status;
	}

	@Override
	public List<Category> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return categoryDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return categoryDAO.total_records(application_no,filters);
	}

		

}
