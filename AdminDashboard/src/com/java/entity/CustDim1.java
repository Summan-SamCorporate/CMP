package com.java.entity;

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



@Entity
@Table(name = "_DIM_CUSTDIM1")

public class CustDim1 {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "custdim1_code")
	private String custdim1_code;
	
	@Column(name = "application_no")
	private String application_no;
	
	@Column(name = "custdim1_description")
	private String custdim1_description;

	@Column(name = "custdim1_attribute1")
	private String custdim1_attribute1;
	
	@Column(name = "custdim1_attribute2")
	private String custdim1_attribute2;
	
	@Column(name = "custdim1_attribute3")
	private String custdim1_attribute3;
	
	@Column(name = "custdim1_attribute4")
	private String custdim1_attribute4;
	
	@Column(name = "custdim1_attribute5")
	private String custdim1_attribute5;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "column_status")
	private String column_status;
	
	@Column(name = "default_flag")
	private String default_flag;

	public String getCustdim1_code() {
		return custdim1_code;
	}

	public void setCustdim1_code(String custdim1_code) {
		this.custdim1_code = custdim1_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getCustdim1_description() {
		return custdim1_description;
	}

	public void setCustdim1_description(String custdim1_description) {
		this.custdim1_description = custdim1_description;
	}

	public String getCustdim1_attribute1() {
		return custdim1_attribute1;
	}

	public void setCustdim1_attribute1(String custdim1_attribute1) {
		this.custdim1_attribute1 = custdim1_attribute1;
	}

	public String getCustdim1_attribute2() {
		return custdim1_attribute2;
	}

	public void setCustdim1_attribute2(String custdim1_attribute2) {
		this.custdim1_attribute2 = custdim1_attribute2;
	}

	public String getCustdim1_attribute3() {
		return custdim1_attribute3;
	}

	public void setCustdim1_attribute3(String custdim1_attribute3) {
		this.custdim1_attribute3 = custdim1_attribute3;
	}

	public String getCustdim1_attribute4() {
		return custdim1_attribute4;
	}

	public void setCustdim1_attribute4(String custdim1_attribute4) {
		this.custdim1_attribute4 = custdim1_attribute4;
	}

	public String getCustdim1_attribute5() {
		return custdim1_attribute5;
	}

	public void setCustdim1_attribute5(String custdim1_attribute5) {
		this.custdim1_attribute5 = custdim1_attribute5;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDefault_flag() {
		return default_flag;
	}

	public void setDefault_flag(String default_flag) {
		this.default_flag = default_flag;
	}

	
}
	