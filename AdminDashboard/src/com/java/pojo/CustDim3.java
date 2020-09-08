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


public class CustDim3 {
	private String custdim3_code;
	private String application_no;
	private String custdim3_description;
	private String custdim3_attribute1;
	private String custdim3_attribute2;
	private String custdim3_attribute3;
	private String custdim3_attribute4;
	private String custdim3_attribute5;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String column_status;

	public String getCustdim3_code() {
		return custdim3_code;
	}

	public void setCustdim3_code(String custdim3_code) {
		this.custdim3_code = custdim3_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getCustdim3_description() {
		return custdim3_description;
	}

	public void setCustdim3_description(String custdim3_description) {
		this.custdim3_description = custdim3_description;
	}

	public String getCustdim3_attribute1() {
		return custdim3_attribute1;
	}

	public void setCustdim3_attribute1(String custdim3_attribute1) {
		this.custdim3_attribute1 = custdim3_attribute1;
	}

	public String getCustdim3_attribute2() {
		return custdim3_attribute2;
	}

	public void setCustdim3_attribute2(String custdim3_attribute2) {
		this.custdim3_attribute2 = custdim3_attribute2;
	}

	public String getCustdim3_attribute3() {
		return custdim3_attribute3;
	}

	public void setCustdim3_attribute3(String custdim3_attribute3) {
		this.custdim3_attribute3 = custdim3_attribute3;
	}

	public String getCustdim3_attribute4() {
		return custdim3_attribute4;
	}

	public void setCustdim3_attribute4(String custdim3_attribute4) {
		this.custdim3_attribute4 = custdim3_attribute4;
	}

	public String getCustdim3_attribute5() {
		return custdim3_attribute5;
	}

	public void setCustdim3_attribute5(String custdim3_attribute5) {
		this.custdim3_attribute5 = custdim3_attribute5;
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
	