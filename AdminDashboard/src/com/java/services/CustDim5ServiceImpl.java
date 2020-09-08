package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.CustDim5DAO;
import com.java.dao.CustDim5DAOImpl;
import com.java.entity.Status;
import com.java.entity.CustDim10;
import com.java.entity.CustDim5;
import com.java.pojo.Filter;

public class CustDim5ServiceImpl implements CustDim5Service {

	private CustDim5DAO custdimDAO = new CustDim5DAOImpl();

	@Override
	public List<CustDim5> getAll(String application_no) {
		return custdimDAO.list(application_no);
	}

	@Override
	public Status addNewCustDim(CustDim5 new_custDim) {
		
		Status status = custdimDAO.add(new_custDim);
		log.info(new_custDim.getCustdim5_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editCustDim(CustDim5 new_custDim) {
		
		//Add default values
		log.info("Editting Cust Dim5 "+new_custDim.getCustdim5_code());
		
		custdimDAO.update(new_custDim);
		return null;
	}

	@Override
	public Status remove(CustDim5 new_custDim) {
	
		Status status =custdimDAO.delete(new_custDim);
		if(status.getCode()==0){
		log.info("Removed Cust Dim1 "+new_custDim.getCustdim5_code());
		}
		return status;
	}

	
	@Override
	public List<CustDim5> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return custdimDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return custdimDAO.total_records(application_no,filters);
	}
	
}
