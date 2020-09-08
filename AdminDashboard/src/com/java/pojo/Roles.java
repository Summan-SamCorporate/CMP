package com.java.pojo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Roles{
	private String role_code;
	private String role_description;
	private String role_attribute1;
	private String role_attribute2;
	private String role_attribute3;
	private String role_attribute4;
	private String role_attribute5;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String column_status;

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getRole_description() {
		return role_description;
	}

	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}

	public String getRole_attribute1() {
		return role_attribute1;
	}

	public void setRole_attribute1(String role_attribute1) {
		this.role_attribute1 = role_attribute1;
	}

	public String getRole_attribute2() {
		return role_attribute2;
	}

	public void setRole_attribute2(String role_attribute2) {
		this.role_attribute2 = role_attribute2;
	}

	public String getRole_attribute3() {
		return role_attribute3;
	}

	public void setRole_attribute3(String role_attribute3) {
		this.role_attribute3 = role_attribute3;
	}

	public String getRole_attribute4() {
		return role_attribute4;
	}

	public void setRole_attribute4(String role_attribute4) {
		this.role_attribute4 = role_attribute4;
	}

	public String getRole_attribute5() {
		return role_attribute5;
	}

	public void setRole_attribute5(String role_attribute5) {
		this.role_attribute5 = role_attribute5;
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

	}
