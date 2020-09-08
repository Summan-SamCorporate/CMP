package com.java.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Category_Type")
public class CategoryType {

	@Id
	@Column(name = "category_type")
	private String category_type;
	
	@Column(name = "description")
	private String description;

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


	@Column(name = "application_no")
	private String application_no;


	public String getCategory_type() {
		return category_type;
	}


	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public String getApplication_no() {
		return application_no;
	}


	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}
	
}
