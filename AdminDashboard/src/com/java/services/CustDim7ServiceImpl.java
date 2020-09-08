package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CustDim7DAO;
import com.java.dao.CustDim7DAOImpl;
import com.java.entity.Status;
import com.java.entity.CustDim10;
import com.java.entity.CustDim7;
import com.java.pojo.Filter;

public class CustDim7ServiceImpl implements CustDim7Service {

	private CustDim7DAO custdimDAO = new CustDim7DAOImpl();

	@Override
	public List<CustDim7> getAll(String application_no) {
		return custdimDAO.list(application_no);
	}

	@Override
	public Status addNewCustDim(CustDim7 new_custDim) {
		
		Status status = custdimDAO.add(new_custDim);
		log.info(new_custDim.getCustdim7_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCustDim(CustDim7 new_custDim) {
		
		//Add default values
		log.info("Editting Cust Dim7 "+new_custDim.getCustdim7_code());
		
		custdimDAO.update(new_custDim);
		return null;
	}

	@Override
	public Status remove(CustDim7 new_custDim) {
	
		Status status =custdimDAO.delete(new_custDim);
		if(status.getCode()==0){
		log.info("Removed Cust Dim7 "+new_custDim.getCustdim7_code());
		}
		return status;
	}

	
	@Override
	public List<CustDim7> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return custdimDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return custdimDAO.total_records(application_no,filters);
	}
	
}
