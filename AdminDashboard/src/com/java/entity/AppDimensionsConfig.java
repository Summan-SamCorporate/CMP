package com.java.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/*
 * Created By: Summan Bahadur
 * Description: Saving configuration details for custom dimensions
 */

@Entity
@Table(name = "APP_DIMENSIONS_CONFIG")

public class AppDimensionsConfig {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "application_no")
	private String application_no;


	@Column(name = "default_value")
	private String default_value;

	@Column(name = "visibility")
	private Boolean visibility;
	
	@Column(name = "default_label")
	private String default_label;
	
	@Column(name = "customised_label")
	private String customised_label;
	
	@Column(name = "url")
	private String url;

	@Column(name = "url_hierarchy")
	private String url_hierarchy;

	@Column(name = "column_status")
	private String column_status;

	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;

	public AppDimensionsConfig(){
		super();
	}
	public AppDimensionsConfig(String application_no, String default_label, Boolean visibility, String customised_label,
			String url, String url_hierarchy,String column_status) {
		super();
		this.application_no = application_no;
		this.default_label = default_label;
		this.visibility = visibility;
		this.customised_label = customised_label;
		this.url = url;
		this.url_hierarchy = url_hierarchy;
		this.column_status = column_status;
	}
	
	/*Parameter Constructor for default creation*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDefault_label() {
		return default_label;
	}
	public void setDefault_label(String default_label) {
		this.default_label = default_label;
	}
	public String getCustomised_label() {
		return customised_label;
	}
	public void setCustomised_label(String customised_label) {
		this.customised_label = customised_label;
	}
	public String getUrl_hierarchy() {
		return url_hierarchy;
	}
	public void setUrl_hierarchy(String url_hierarchy) {
		this.url_hierarchy = url_hierarchy;
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


	
	
}
	