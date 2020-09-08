package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.ContainerDAO;
import com.java.dao.ContainerDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.Container;
import com.java.pojo.Filter;

public class ContainerServiceImpl implements ContainerService {

	private ContainerDAO containerDAO = new ContainerDAOImpl();


	@Override
	public List<Container> getAll() {
		
		return containerDAO.list();
	}

	@Override
	public List<Container> getAll(String user_name) {
		
		return containerDAO.list(user_name);
	}

	@Override
	public Status addNewContainer(Container new_container) {
		
		Status status = containerDAO.add(new_container);
		log.info("New Container "+new_container.getContainer_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editContainer(Container container) {
		
		//Add default values
		log.info("Editting Container "+container.getContainer_code());		
		containerDAO.Update(container);
		return null;
	}

	@Override
	public Status remove(Container container) {
		// TODO Auto-generated method stub
		Status status= containerDAO.delete(container);
		if(status.getCode()==0){
			log.info("Removed Container "+container.getContainer_code());
			}
		return status;
		
	}

	@Override
	public List<Container> getAll(String user_name,int start_index, int limit,List<Filter> filters) {

		return containerDAO.getAll(user_name,start_index,limit,filters);
	}

	@Override
	public int totalCount(String user_name,List<Filter> filters) {
		return containerDAO.total_records(user_name,filters);
	}

}
