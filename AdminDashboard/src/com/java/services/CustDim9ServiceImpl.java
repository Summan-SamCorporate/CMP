package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CustDim9DAO;
import com.java.dao.CustDim9DAOImpl;
import com.java.entity.Status;
import com.java.entity.CustDim10;
import com.java.entity.CustDim9;
import com.java.pojo.Filter;

public class CustDim9ServiceImpl implements CustDim9Service {

	private CustDim9DAO custdimDAO = new CustDim9DAOImpl();

	@Override
	public List<CustDim9> getAll(String application_no) {
		return custdimDAO.list(application_no);
	}

	@Override
	public Status addNewCustDim(CustDim9 new_custDim) {
		
		Status status = custdimDAO.add(new_custDim);
		log.info(new_custDim.getCustdim9_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCustDim(CustDim9 new_custDim) {
		
		//Add default values
		log.info("Editting Cust Dim9 "+new_custDim.getCustdim9_code());
		
		custdimDAO.update(new_custDim);
		return null;
	}

	@Override
	public Status remove(CustDim9 new_custDim) {
	
		Status status =custdimDAO.delete(new_custDim);
		if(status.getCode()==0){
		log.info("Removed Cust Dim9"+new_custDim.getCustdim9_code());
		}
		return status;
	}

	@Override
	public List<CustDim9> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return custdimDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return custdimDAO.total_records(application_no,filters);
	}
	

}
