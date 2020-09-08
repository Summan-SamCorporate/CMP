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
@Table(name = "Container")

public class Container {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "container_code")
	private String container_code;
	
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

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getContainer_code() {
		return container_code;
	}


	public void setContainer_code(String container_code) {
		this.container_code = container_code;
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
}
	