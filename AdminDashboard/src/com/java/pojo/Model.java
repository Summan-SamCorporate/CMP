package com.java.pojo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class Model{

	private String model_code;
	private String model_description;
	private String model_attribute_01;
	private String model_attribute_02;
	private String model_attribute_03;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String column_status;

	public String getModel_code() {
		return model_code;
	}

	public void setModel_code(String model_code) {
		this.model_code = model_code;
	}

	public String getModel_description() {
		return model_description;
	}

	public void setModel_description(String model_description) {
		this.model_description = model_description;
	}

	public String getModel_attribute_01() {
		return model_attribute_01;
	}

	public void setModel_attribute_01(String model_attribute_01) {
		this.model_attribute_01 = model_attribute_01;
	}



	public String getModel_attribute_03() {
		return model_attribute_03;
	}

	public void setModel_attribute_03(String model_attribute_03) {
		this.model_attribute_03 = model_attribute_03;
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

	public String getModel_attribute_02() {
		return model_attribute_02;
	}

	public void setModel_attribute_02(String model_attribute_02) {
		this.model_attribute_02 = model_attribute_02;
	}

	}
