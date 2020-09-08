package com.java.services;

import java.util.Date;

import org.apache.log4j.Logger;

import com.java.entity.Roles;
import com.java.entity.Status;
import com.java.entity.UserStatus;
import com.java.entity.Users;

public interface AppLoginService {

	static Logger log = Logger.getLogger(AppLoginService.class);
	
	public Users checkLogin(String user);
	
	// Changed by Bijoy
/*
	public Users createNewUser(String username, String password, String first_name, String last_name, Roles role,
			UserStatus status, String email, String phone, String mobile, String entity, String department,
			String designation, Date last_login_date, String cod_pwd_rule, Date password_change_date, int no_of_failure,
			String flag_password_admin, String created_user, Date created_date, String updated_user, Date updated_date) ;
*/
	public Status changePassword(Users user);
	public Status changeTheme(Users user);
}
