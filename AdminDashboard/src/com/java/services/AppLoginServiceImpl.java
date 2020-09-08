package com.java.services;

import java.util.Date;
import java.util.List;

import com.java.dao.UsersDAO;
import com.java.dao.UsersDAOImpl;
import com.java.entity.Roles;
import com.java.entity.Status;
import com.java.entity.UserStatus;
import com.java.entity.Users;

public class AppLoginServiceImpl implements AppLoginService {

	private UsersDAO usersDAO = new UsersDAOImpl();

	@Override
	public Users checkLogin(String user) {
		Users users = usersDAO.searchUser(user);

		return users;
	}
	
	// Changed by Bijoy
/*	@Override
	public Users createNewUser(String username, String password, String first_name, String last_name, Roles role,
			UserStatus status, String email, String phone, String mobile, String entity, String department,
			String designation, Date last_login_date, String cod_pwd_rule, Date password_change_date, int no_of_failure,
			String flag_password_admin, String created_user, Date created_date, String updated_user, Date updated_date) {

		Users user = new Users();

		user.setUsername(username);
		user.setPassword(password);
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
	
		user.setRole(role);
		
		user.setStatus(status);
		user.setEmail(email);
		user.setPhone(phone);
		user.setMobile(mobile);
		user.setEntity(entity);
		user.setDepartment(department);
		user.setDesignation(designation);
		user.setLast_login_date(last_login_date);
		user.setCod_pwd_rule(cod_pwd_rule);
		user.setPassword_change_date(password_change_date);
		user.setNo_of_failure(no_of_failure);
		user.setFlag_password_admin(flag_password_admin);
		user.setCreated_user(created_user);
		user.setCreated_date(created_date);
		user.setUpdated_date(updated_date);
		user.setUpdated_user(updated_user);
		return usersDAO.add(user);
	}*/

	@Override
	public Status changePassword(Users user) {
		// TODO Auto-generated method stub
		return usersDAO.changePassword(user);
	}
	
	@Override
	public Status changeTheme(Users user) {
		// TODO Auto-generated method stub
		 usersDAO.update(user);
	
	return Status.SUCCESS;
	}

}
