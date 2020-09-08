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
@Table(name = "_APP_APPLICATION")

public class Application {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "app_refno")
	private String app_refno;
	
	@Column(name = "app_description")
	private String app_description;

	@Column(name = "app_long_description")
	private String app_long_description;


	@OneToOne
	private Model model;

	@OneToOne
	private ApplicationStatus applicationstatus;
	
	@Column(name = "app_owner")
	private String app_owner;
	
	@Column(name = "app_organization")
	private String app_organization;
	
	@Column(name = "app_attribute1")
	private String app_attribute1;
	
	@Column(name = "app_attribute2")
	private String app_attribute2;
	
	@Column(name = "app_attribute3")
	private String app_attribute3;
	
	@Column(name = "app_attribute4")
	private String app_attribute4;
	
	@Column(name = "app_attribute5")
	private String app_attribute5;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "application_status")
	private String application_status;

	
	public String getApp_refno() {
		return app_refno;
	}


	public void setApp_refno(String app_refno) {
		this.app_refno = app_refno;
	}


	public String getApp_description() {
		return app_description;
	}


	public void setApp_description(String app_description) {
		this.app_description = app_description;
	}


	public String getApp_long_description() {
		return app_long_description;
	}


	public void setApp_long_description(String app_long_description) {
		this.app_long_description = app_long_description;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public ApplicationStatus getApplicationstatus() {
		return applicationstatus;
	}


	public void setApplicationstatus(ApplicationStatus applicationstatus) {
		this.applicationstatus = applicationstatus;
	}


	public String getApp_owner() {
		return app_owner;
	}


	public void setApp_owner(String app_owner) {
		this.app_owner = app_owner;
	}


	public String getApp_organization() {
		return app_organization;
	}


	public void setApp_organization(String app_organization) {
		this.app_organization = app_organization;
	}


	public String getApp_attribute1() {
		return app_attribute1;
	}


	public void setApp_attribute1(String app_attribute1) {
		this.app_attribute1 = app_attribute1;
	}


	public String getApp_attribute2() {
		return app_attribute2;
	}


	public void setApp_attribute2(String app_attribute2) {
		this.app_attribute2 = app_attribute2;
	}


	public String getApp_attribute3() {
		return app_attribute3;
	}


	public void setApp_attribute3(String app_attribute3) {
		this.app_attribute3 = app_attribute3;
	}


	public String getApp_attribute4() {
		return app_attribute4;
	}


	public void setApp_attribute4(String app_attribute4) {
		this.app_attribute4 = app_attribute4;
	}


	public String getApp_attribute5() {
		return app_attribute5;
	}


	public void setApp_attribute5(String app_attribute5) {
		this.app_attribute5 = app_attribute5;
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


	public String getApplication_status() {
		return application_status;
	}


	public void setApplication_status(String application_status) {
		this.application_status = application_status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
	