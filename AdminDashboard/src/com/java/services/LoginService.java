/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.services;

import com.java.bean.USERS;
import com.java.dao.LoginDetailsData;

/**
 *
 * @author Bijoy
 */
public class LoginService {
    	USERS user = new USERS();

	public boolean authentication(String userName, String password){
		
		LoginDetailsData ldd = new LoginDetailsData();
		user = ldd.getUserDetails(userName,password);
		
		if((user.getUsername().equals(null) || user.getUsername().trim().equals(""))){
			return false;
		}
		return true;
	}
	
	public USERS getUserDetail(){
		return user;
	}
}
