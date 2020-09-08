package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CustDim8DAO;
import com.java.dao.CustDim8DAOImpl;
import com.java.entity.Status;
import com.java.entity.CustDim10;
import com.java.entity.CustDim8;
import com.java.pojo.Filter;

public class CustDim8ServiceImpl implements CustDim8Service {

	private CustDim8DAO custdimDAO = new CustDim8DAOImpl();

	@Override
	public List<CustDim8> getAll(String application_no) {
		return custdimDAO.list(application_no);
	}

	@Override
	public Status addNewCustDim(CustDim8 new_custDim) {
		
		Status status = custdimDAO.add(new_custDim);
		log.info(new_custDim.getCustdim8_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCustDim(CustDim8 new_custDim) {
		
		//Add default values
		log.info("Editting Cust Dim8 "+new_custDim.getCustdim8_code());
		
		custdimDAO.update(new_custDim);
		return null;
	}

	@Override
	public Status remove(CustDim8 new_custDim) {
	
		Status status =custdimDAO.delete(new_custDim);
		if(status.getCode()==0){
		log.info("Removed Cust Dim8 "+new_custDim.getCustdim8_code());
		}
		return status;
	}

	@Override
	public List<CustDim8> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return custdimDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return custdimDAO.total_records(application_no,filters);
	}
	

}
