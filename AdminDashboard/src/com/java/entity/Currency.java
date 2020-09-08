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
@Table(name = "CURRENCY")

public class Currency {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "currency_code")
	private String currency_code;
	
	@Column(name = "application_no")
	private String application_no;
	
	@Column(name = "currency_description")
	private String currency_description;

	@Column(name = "currency_decimal")
	private String currency_decimal;


	@Column(name = "cur_attribute1")
	private String cur_attribute1;
	
	@Column(name = "cur_attribute2")
	private String cur_attribute2;
	
	@Column(name = "cur_attribute3")
	private String cur_attribute3;
	
	@Column(name = "cur_attribute4")
	private String cur_attribute4;
	
	@Column(name = "cur_attribute5")
	private String cur_attribute5;
	
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

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}



	public String getCur_attribute1() {
		return cur_attribute1;
	}

	public void setCur_attribute1(String cur_attribute1) {
		this.cur_attribute1 = cur_attribute1;
	}

	public String getCur_attribute2() {
		return cur_attribute2;
	}

	public void setCur_attribute2(String cur_attribute2) {
		this.cur_attribute2 = cur_attribute2;
	}

	public String getCur_attribute3() {
		return cur_attribute3;
	}

	public void setCur_attribute3(String cur_attribute3) {
		this.cur_attribute3 = cur_attribute3;
	}

	public String getCur_attribute4() {
		return cur_attribute4;
	}

	public void setCur_attribute4(String cur_attribute4) {
		this.cur_attribute4 = cur_attribute4;
	}

	public String getCur_attribute5() {
		return cur_attribute5;
	}

	public void setCur_attribute5(String cur_attribute5) {
		this.cur_attribute5 = cur_attribute5;
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

	public String getCurrency_description() {
		return currency_description;
	}

	public void setCurrency_description(String currency_description) {
		this.currency_description = currency_description;
	}

	public String getCurrency_decimal() {
		return currency_decimal;
	}

	public void setCurrency_decimal(String currency_decimal) {
		this.currency_decimal = currency_decimal;
	}

	}
	