package com.java.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


public class Scenario  implements Serializable {
	private String scenario_code;
	private String application_no;
	private String scenario_description; 
	private String version;
	private String year;
	private String previous_scenario;
	private String next_scenario;
	private String ref_scenario1;
	private String ref_scenario2;
	private String ref_scenario3;
	private String ref_scenario4;
	private String ref_scenario5;
	private String ref_scenario6;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String scenario_status;
	public String getScenario_code() {
		return scenario_code;
	}

	public void setScenario_code(String scenario_code) {
		this.scenario_code = scenario_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getScenario_description() {
		return scenario_description;
	}

	public void setScenario_description(String scenario_description) {
		this.scenario_description = scenario_description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public String getPrevious_scenario() {
		return previous_scenario;
	}

	public void setPrevious_scenario(String previous_scenario) {
		this.previous_scenario = previous_scenario;
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

	public String getScenario_status() {
		return scenario_status;
	}

	public void setScenario_status(String scenario_status) {
		this.scenario_status = scenario_status;
	}

	public String getNext_scenario() {
		return next_scenario;
	}

	public void setNext_scenario(String next_scenario) {
		this.next_scenario = next_scenario;
	}

	public String getRef_scenario1() {
		return ref_scenario1;
	}

	public void setRef_scenario1(String ref_scenario1) {
		this.ref_scenario1 = ref_scenario1;
	}

	public String getRef_scenario2() {
		return ref_scenario2;
	}

	public void setRef_scenario2(String ref_scenario2) {
		this.ref_scenario2 = ref_scenario2;
	}

	public String getRef_scenario3() {
		return ref_scenario3;
	}

	public void setRef_scenario3(String ref_scenario3) {
		this.ref_scenario3 = ref_scenario3;
	}

	public String getRef_scenario4() {
		return ref_scenario4;
	}

	public void setRef_scenario4(String ref_scenario4) {
		this.ref_scenario4 = ref_scenario4;
	}

	public String getRef_scenario5() {
		return ref_scenario5;
	}

	public void setRef_scenario5(String ref_scenario5) {
		this.ref_scenario5 = ref_scenario5;
	}

	public String getRef_scenario6() {
		return ref_scenario6;
	}

	public void setRef_scenario6(String ref_scenario6) {
		this.ref_scenario6 = ref_scenario6;
	}

}
	