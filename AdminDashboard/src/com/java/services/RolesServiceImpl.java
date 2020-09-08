package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.RolesDAO;
import com.java.dao.RolesDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;
import com.java.entity.Roles;

public class RolesServiceImpl implements RolesService {

	private RolesDAO roleDAO = new RolesDAOImpl();

	@Override
	public List<Roles> getAll() {
		// TODO Auto-generated method stub
		return roleDAO.list();
	}
	@Override
	public List<Roles> get() {
		// TODO Auto-generated method stub
		ArrayList<Roles> roles = new ArrayList<Roles>();
		
		ArrayList<Roles> roles_list = (ArrayList<Roles>) roleDAO.list();
		for(int i=0; i< roles_list.size();i++){
			
			Roles r = roles_list.get(i);
			
			if(!r.getRole_code().equals("superadmin")){
				roles.add(r);
			}
		}
		return roles;
	}

	@Override
	public Status addNewRole(Roles new_role) {
		
		//Add default values
		new_role.setCreated_date(new Date());
		new_role.setUpdated_date(new Date());
		Status status = roleDAO.add(new_role);
		log.info(new_role.getRole_code() + " Added Successfully");
		
		return status;
	}
	@Override
	public Status editRole(Roles role) {
		
		//Add default values
		log.info("Editting Role "+role.getRole_code());
		
		role.setUpdated_date(new Date());
		return roleDAO.update(role);
	}

	@Override
	public Status remove(Roles new_role) {
		// TODO Auto-generated method stub
		Status status =roleDAO.delete(new_role);
		if(status.getCode()==0){
		log.info("Removed Role "+new_role.getRole_code());
		}
		return status;
	}
	@Override
	public List<Roles> getAll( int start_index, int limit,List<Filter> filters) {

		return roleDAO.getAll(start_index,limit,filters);
	}

	@Override
	public int totalCount(List<Filter> filters) {
		return roleDAO.total_records(filters);
	}

}
