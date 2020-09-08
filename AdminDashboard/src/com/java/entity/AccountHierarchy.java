package com.java.entity;

import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "_DIM_ACCOUNT_HIERARCHY")

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHierarchy {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_hierarchy_code")
	private String account_hierarchy_code;
	
	@Column(name = "code_description")
	private String code_description;
	
	@Column(name = "application_no")
	private String application_no;

	
	@Column(name = "root")
	private String root;

	@Column(name = "root_description")
	private String root_description;
	
	@Column(name = "enable")
	private Boolean enable;
	
	
	@OneToOne( fetch=FetchType.EAGER)
	 @JoinColumn(name = "version_code")
	private Account_Version version;

	
	@Column(name = "attribute1")
	private String attribute1;
	
	@Column(name = "attribute2")
	private String attribute2;
		
	@Column(name = "attribute3")
	private String attribute3;
	
	@Column(name = "attribute4")
	private String attribute4;
	
	@Column(name = "attribute5")
	private String attribute5;
	
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

	public String getAccount_hierarchy_code() {
		return account_hierarchy_code;
	}

	public void setAccount_hierarchy_code(String account_hierarchy_code) {
		this.account_hierarchy_code = account_hierarchy_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}


	public String getAccount_attr1() {
		return attribute1;
	}

	public void setAccount_attr1(String attribute1) {
		this.attribute1 = attribute1;
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

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}


	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getCode_description() {
		return code_description;
	}

	public void setCode_description(String code_description) {
		this.code_description = code_description;
	}

	public String getRoot_description() {
		return root_description;
	}

	public void setRoot_description(String root_description) {
		this.root_description = root_description;
	}

	public Account_Version getVersion() {
		return version;
	}

	public void setVersion(Account_Version version) {
		this.version = version;
	}



	
}
	