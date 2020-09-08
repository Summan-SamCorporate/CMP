package com.java.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class Users {

	private String username;
	private String password;
	private String first_name;
	private String last_name; 
	private String role_role_code;
	private String status_status_code;
	private String email;
	private String phone;
	private String mobile;
	private String entity;
	private String department;
	private String designation;
	private Date last_login_date;
	private String cod_pwd_rule;
	private Date password_change_date;
	private int no_of_failure;	
	private int session_time_out;
	private String flag_password_admin;
	private String created_user;
	private Date created_date;
	private String updated_user;
	private Date updated_date;
	private String column_status;
	private String stylesheet_name;
	
	public String getStylesheet_name() {
		return stylesheet_name;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getCod_pwd_rule() {
		return cod_pwd_rule;
	}

	public void setCod_pwd_rule(String cod_pwd_rule) {
		this.cod_pwd_rule = cod_pwd_rule;
	}

	public Date getPassword_change_date() {
		return password_change_date;
	}

	public void setPassword_change_date(Date password_change_date) {
		this.password_change_date = password_change_date;
	}

	public int getNo_of_failure() {
		return no_of_failure;
	}

	public void setNo_of_failure(int no_of_failure) {
		this.no_of_failure = no_of_failure;
	}

	public String getFlag_password_admin() {
		return flag_password_admin;
	}

	public void setFlag_password_admin(String flag_password_admin) {
		this.flag_password_admin = flag_password_admin;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getUpdated_user() {
		return updated_user;
	}

	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getColumn_status() {
		return column_status;
	}

	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}

	public int getSession_time_out() {
		return session_time_out;
	}


	public void setSession_time_out(int session_time_out) {
		this.session_time_out = session_time_out;
	}



	public void setStylesheet_name(String stylesheet_name) {
		this.stylesheet_name = stylesheet_name;
	}


	public String getRole_role_code() {
		return role_role_code;
	}


	public void setRole_role_code(String role_role_code) {
		this.role_role_code = role_role_code;
	}


	public String getStatus_status_code() {
		return status_status_code;
	}


	public void setStatus_status_code(String status_status_code) {
		this.status_status_code = status_status_code;
	}
}