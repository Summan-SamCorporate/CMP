package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.AccountVersionDAO;
import com.java.dao.AccountVersionDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Account_Version;
import com.java.entity.Scenario_Version;

public class AccountVersionServiceImpl implements AccountVersionService {

	private AccountVersionDAO versionDAO = new AccountVersionDAOImpl();

	@Override
	public List<Account_Version> getAll() {
		// TODO Auto-generated method stub
		return versionDAO.list();
	}

	@Override
	public Status addNewVersion(Account_Version new_version) {
		
		Status status = versionDAO.add(new_version);
		log.info(new_version.getVersion_code()+ " Added Successfully");
		
		return status;
	}
	@Override
	public Status editVersion(Account_Version version) {
		
		//Add default values
		log.info("Editting Version  "+version.getVersion_code());		
		versionDAO.Update(version);
		return null;
	}

	@Override
	public Status remove(Account_Version version) {
		// TODO Auto-generated method stub
	
		Status status =versionDAO.delete(version);
		if(status.getCode()==0){
		log.info("Removed Version Status "+version.getVersion_code());
		}
		return status;
	}

	@Override
	public List<Account_Version> getAll( int start_index, int limit,List<Filter> filters) {

		return versionDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return versionDAO.total_records(filters);
	}


}
