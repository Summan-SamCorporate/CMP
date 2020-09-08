package com.java.pojo;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Entities {


	private String entity_code;
	private String application_no;
	private String entity_description;
	private String currency_currency_code;
	private String business_name;
	private String legal_hq_name;
	private String legal_hq_location;
	private String administrative_hq;
	private String entity_location;
	private String main_office;
	private String manager;
	private String entity_email;
	private String entity_phone;
	private String entity_country;
	private String entity_attr1;
	private String entity_attr2;
	private String entity_attr3;
	private String entity_attr4;
	private String entity_attr5;
	private String entity_attr6;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String column_status;

	public String getEntity_code() {
		return entity_code;
	}

	public void setEntity_code(String entity_code) {
		this.entity_code = entity_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getEntity_description() {
		return entity_description;
	}

	public void setEntity_description(String entity_description) {
		this.entity_description = entity_description;
	}
	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getLegal_hq_name() {
		return legal_hq_name;
	}

	public void setLegal_hq_name(String legal_hq_name) {
		this.legal_hq_name = legal_hq_name;
	}

	public String getLegal_hq_location() {
		return legal_hq_location;
	}

	public void setLegal_hq_location(String legal_hq_location) {
		this.legal_hq_location = legal_hq_location;
	}

	public String getAdministrative_hq() {
		return administrative_hq;
	}

	public void setAdministrative_hq(String administrative_hq) {
		this.administrative_hq = administrative_hq;
	}

	public String getEntity_location() {
		return entity_location;
	}

	public void setEntity_location(String entity_location) {
		this.entity_location = entity_location;
	}

	public String getMain_office() {
		return main_office;
	}

	public void setMain_office(String main_office) {
		this.main_office = main_office;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getEntity_email() {
		return entity_email;
	}

	public void setEntity_email(String entity_email) {
		this.entity_email = entity_email;
	}

	public String getEntity_phone() {
		return entity_phone;
	}

	public void setEntity_phone(String entity_phone) {
		this.entity_phone = entity_phone;
	}

	public String getEntity_country() {
		return entity_country;
	}

	public void setEntity_country(String entity_country) {
		this.entity_country = entity_country;
	}

	public String getEntity_attr1() {
		return entity_attr1;
	}

	public void setEntity_attr1(String entity_attr1) {
		this.entity_attr1 = entity_attr1;
	}

	public String getEntity_attr2() {
		return entity_attr2;
	}

	public void setEntity_attr2(String entity_attr2) {
		this.entity_attr2 = entity_attr2;
	}

	public String getEntity_attr3() {
		return entity_attr3;
	}

	public void setEntity_attr3(String entity_attr3) {
		this.entity_attr3 = entity_attr3;
	}

	public String getEntity_attr4() {
		return entity_attr4;
	}

	public void setEntity_attr4(String entity_attr4) {
		this.entity_attr4 = entity_attr4;
	}

	public String getEntity_attr5() {
		return entity_attr5;
	}

	public void setEntity_attr5(String entity_attr5) {
		this.entity_attr5 = entity_attr5;
	}

	public String getEntity_attr6() {
		return entity_attr6;
	}

	public void setEntity_attr6(String entity_attr6) {
		this.entity_attr6 = entity_attr6;
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

	public String getCurrency_currency_code() {
		return currency_currency_code;
	}

	public void setCurrency_currency_code(String currency_currency_code) {
		this.currency_currency_code = currency_currency_code;
	}


}
	