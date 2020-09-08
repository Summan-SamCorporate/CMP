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

/*
 * Created By: Summan Bahadur
 * 
 * Obsolete Requirement
 */

@Entity
@Table(name = "menu_config")

public class Menu_Config {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "default_value")
	private String default_value;

	@Column(name = "visibility")
	private Boolean visibility;
	
	@Column(name = "customised_value")
	private String customised_value;
	
	@Column(name = "url")
	private String url;

	@Column(name = "column_status")
	private String column_status;


	public String getDefault_value() {
		return default_value;
	}





	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}





	public Boolean getVisibility() {
		return visibility;
	}





	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}





	public String getCustomised_value() {
		return customised_value;
	}





	public void setCustomised_value(String customised_value) {
		this.customised_value = customised_value;
	}





	public String getUrl() {
		return url;
	}





	public void setUrl(String url) {
		this.url = url;
	}





	public String getColumn_status() {
		return column_status;
	}





	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}

	
}
	