package com.java.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Dictionary")

public class Dictionary implements java.io.Serializable{

	@Id
	@Column(name = "dictionary_code")
	private String dictionary_code;
	
	@Column(name = "description")
	private String description;

	@Column(name = "column_name")
	private String column_name;
		
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

	public String getDictionary_code() {
		return dictionary_code;
	}

	public void setDictionary_code(String dictionary_code) {
		this.dictionary_code = dictionary_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
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
