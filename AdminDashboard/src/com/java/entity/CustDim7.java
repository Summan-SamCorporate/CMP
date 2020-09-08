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
@Table(name = "_DIM_CUSTDIM7")

public class CustDim7 {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "custdim7_code")
	private String custdim7_code;
	
	@Column(name = "application_no")
	private String application_no;
	
	@Column(name = "custdim7_description")
	private String custdim7_description;

	@Column(name = "custdim7_attribute1")
	private String custdim7_attribute1;
	
	@Column(name = "custdim7_attribute2")
	private String custdim7_attribute2;
	
	@Column(name = "custdim7_attribute3")
	private String custdim7_attribute3;
	
	@Column(name = "custdim7_attribute4")
	private String custdim7_attribute4;
	
	@Column(name = "custdim7_attribute5")
	private String custdim7_attribute5;
	
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
	
	public String getCustdim7_code() {
		return custdim7_code;
	}

	public void setCustdim7_code(String custdim7_code) {
		this.custdim7_code = custdim7_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getCustdim7_description() {
		return custdim7_description;
	}

	public void setCustdim7_description(String custdim7_description) {
		this.custdim7_description = custdim7_description;
	}

	public String getCustdim7_attribute1() {
		return custdim7_attribute1;
	}

	public void setCustdim7_attribute1(String custdim7_attribute1) {
		this.custdim7_attribute1 = custdim7_attribute1;
	}

	public String getCustdim7_attribute2() {
		return custdim7_attribute2;
	}

	public void setCustdim7_attribute2(String custdim7_attribute2) {
		this.custdim7_attribute2 = custdim7_attribute2;
	}

	public String getCustdim7_attribute3() {
		return custdim7_attribute3;
	}

	public void setCustdim7_attribute3(String custdim7_attribute3) {
		this.custdim7_attribute3 = custdim7_attribute3;
	}

	public String getCustdim7_attribute4() {
		return custdim7_attribute4;
	}

	public void setCustdim7_attribute4(String custdim7_attribute4) {
		this.custdim7_attribute4 = custdim7_attribute4;
	}

	public String getCustdim7_attribute5() {
		return custdim7_attribute5;
	}

	public void setCustdim7_attribute5(String custdim7_attribute5) {
		this.custdim7_attribute5 = custdim7_attribute5;
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
	