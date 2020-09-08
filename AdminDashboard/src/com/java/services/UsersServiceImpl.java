package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.UsersDAO;
import com.java.dao.UsersDAOImpl;
import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Status;
import com.java.entity.Users;

public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO = new UsersDAOImpl();

	@Override
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return usersDAO.list();
	}

	@Override
	public Status addNewUser(Users new_user) {
		
		//Add default values
		if(new_user.getUsername().equalsIgnoreCase("superadmin")){
			new_user.setColumn_status("B");
		}
		new_user.setFlag_password_admin("Y");
		Status status = usersDAO.add(new_user);
		log.info(new_user.getUsername() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editUser(Users user) {
		
		//Add default values
		log.info("Editting User "+user.getUsername());
		
		usersDAO.update(user);
		return null;
	}

	@Override
	public Status remove(Users new_user) {
		// TODO Auto-generated method stub
		
		Status status = usersDAO.delete(new_user);
		if(status.getCode()==0){
		log.info("Removed User "+new_user.getUsername());
		}
		return status;

	}

	@Override
	public Users get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getAll( int start_index, int limit,List<Filter> filters) {

		return usersDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return usersDAO.total_records(filters);
	}

}
