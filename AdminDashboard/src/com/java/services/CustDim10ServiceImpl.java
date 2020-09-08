package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CustDim10DAO;
import com.java.dao.CustDim10DAOImpl;
import com.java.entity.Status;
import com.java.entity.Currency;
import com.java.entity.CustDim10;
import com.java.pojo.Filter;

public class CustDim10ServiceImpl implements CustDim10Service {

	private CustDim10DAO custdimDAO = new CustDim10DAOImpl();

	@Override
	public List<CustDim10> getAll(String application_no) {
		return custdimDAO.list(application_no);
	}

	@Override
	public Status addNewCustDim(CustDim10 new_custDim) {
		
		Status status = custdimDAO.add(new_custDim);
		log.info(new_custDim.getCustdim10_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCustDim(CustDim10 new_custDim) {
		
		//Add default values
		log.info("Editting Cust Dim10 "+new_custDim.getCustdim10_code());
		
		custdimDAO.update(new_custDim);
		return null;
	}

	@Override
	public Status remove(CustDim10 new_custDim) {
	
		Status status =custdimDAO.delete(new_custDim);
		if(status.getCode()==0){
		log.info("Removed Cust Dim10 "+new_custDim.getCustdim10_code());
		}
		return status;
	}


	@Override
	public List<CustDim10> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return custdimDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return custdimDAO.total_records(application_no,filters);
	}
	

}
