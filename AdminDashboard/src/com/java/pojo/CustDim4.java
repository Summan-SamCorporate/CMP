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


public class CustDim4 {

	private String custdim4_code;
	private String application_no;
	private String custdim4_description;
	private String custdim4_attribute1;
	private String custdim4_attribute2;
	private String custdim4_attribute3;
	private String custdim4_attribute4;
	private String custdim4_attribute5;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String column_status;

	public String getCustdim4_code() {
		return custdim4_code;
	}

	public void setCustdim4_code(String custdim4_code) {
		this.custdim4_code = custdim4_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getCustdim4_description() {
		return custdim4_description;
	}

	public void setCustdim4_description(String custdim4_description) {
		this.custdim4_description = custdim4_description;
	}

	public String getCustdim4_attribute1() {
		return custdim4_attribute1;
	}

	public void setCustdim4_attribute1(String custdim4_attribute1) {
		this.custdim4_attribute1 = custdim4_attribute1;
	}

	public String getCustdim4_attribute2() {
		return custdim4_attribute2;
	}

	public void setCustdim4_attribute2(String custdim4_attribute2) {
		this.custdim4_attribute2 = custdim4_attribute2;
	}

	public String getCustdim4_attribute3() {
		return custdim4_attribute3;
	}

	public void setCustdim4_attribute3(String custdim4_attribute3) {
		this.custdim4_attribute3 = custdim4_attribute3;
	}

	public String getCustdim4_attribute4() {
		return custdim4_attribute4;
	}

	public void setCustdim4_attribute4(String custdim4_attribute4) {
		this.custdim4_attribute4 = custdim4_attribute4;
	}

	public String getCustdim4_attribute5() {
		return custdim4_attribute5;
	}

	public void setCustdim4_attribute5(String custdim4_attribute5) {
		this.custdim4_attribute5 = custdim4_attribute5;
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
	