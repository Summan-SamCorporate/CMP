package com.java.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "PERIOD")

public class Period {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "period_code")
	private String period_code;
	
	@Column(name = "application_no")
	private String application_no;

	@Column(name = "period_name")
	private String period_name;

	@Column(name = "month_name")
	private String month_name;


	@Column(name="quarter_name")
	private String quarter_name;

	@Column(name = "four_month_name")
	private String four_month_name;
	
	@Column(name = "semester_name")
	private String semester_name;
	
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
	
	//------Many to Many relation with Scenario---------------//
	@JsonIgnore
	@ManyToMany(mappedBy = "periods")
	 private Set<Scenario> scenarioes = new HashSet<>();
	 

	public Set<Scenario> getScenarioes() {
		return scenarioes;
	}

	public void setScenarioes(Set<Scenario> scenarioes) {
		this.scenarioes = scenarioes;
	}

	public String getPeriod_code() {
		return period_code;
	}

	public void setPeriod_code(String period_code) {
		this.period_code = period_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getPeriod_name() {
		return period_name;
	}

	public void setPeriod_name(String period_name) {
		this.period_name = period_name;
	}

	public String getMonth_name() {
		return month_name;
	}

	public void setMonth_name(String month_name) {
		this.month_name = month_name;
	}

	public String getQuarter_name() {
		return quarter_name;
	}

	public void setQuarter_name(String quarter_name) {
		this.quarter_name = quarter_name;
	}

	public String getFour_month_name() {
		return four_month_name;
	}

	public void setFour_month_name(String four_month_name) {
		this.four_month_name = four_month_name;
	}

	public String getSemester_name() {
		return semester_name;
	}

	public void setSemester_name(String semester_name) {
		this.semester_name = semester_name;
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

		
}
	