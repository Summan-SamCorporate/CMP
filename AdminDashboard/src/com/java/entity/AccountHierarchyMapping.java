package com.java.entity;

import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "_DIM_ACCOUNT_HIERARCHY_MAPPING")

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHierarchyMapping {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mapping_id;
	
	@Column(name = "account_hierarchy_code")
	private String account_hierarchy_code;
	
	@Column(name = "account_code")
	private String account_code;
	
	@Column(name = "account_description")
	private String account_description;

	@Column(name = "node_code")
	private String node_code;
	
	@Column(name = "node_description")
	private String node_description;

	@Column(name = "application_no")
	private String application_no;

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


	public String getAccount_code() {
		return account_code;
	}

	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	public String getAccount_description() {
		return account_description;
	}

	public void setAccount_description(String account_description) {
		this.account_description = account_description;
	}

	public String getNode_code() {
		return node_code;
	}

	public void setNode_code(String node_code) {
		this.node_code = node_code;
	}

	public String getNode_description() {
		return node_description;
	}

	public void setNode_description(String node_description) {
		this.node_description = node_description;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
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

	public int getMapping_id() {
		return mapping_id;
	}

	public void setMapping_id(int mapping_id) {
		this.mapping_id = mapping_id;
	}

	public String getAccount_hierarchy_code() {
		return account_hierarchy_code;
	}

	public void setAccount_hierarchy_code(String account_hierarchy_code) {
		this.account_hierarchy_code = account_hierarchy_code;
	}

		
}
	