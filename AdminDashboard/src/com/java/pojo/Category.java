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


public class Category {

	private String category_code;
	private String application_no;
	private String category_description;
	private String category_type_category_type;
	private String cat_attribute1;
	private String cat_attribute2;
	private String cat_attribute3;
	private String cat_attribute4;
	private String cat_attribute5;
	private String created_user;
	private Date created_date;
	private Date updated_date;
	private String column_status;

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}


	public String getCat_attribute1() {
		return cat_attribute1;
	}

	public void setCat_attribute1(String cat_attribute1) {
		this.cat_attribute1 = cat_attribute1;
	}

	public String getCat_attribute2() {
		return cat_attribute2;
	}

	public void setCat_attribute2(String cat_attribute2) {
		this.cat_attribute2 = cat_attribute2;
	}

	public String getCat_attribute3() {
		return cat_attribute3;
	}

	public void setCat_attribute3(String cat_attribute3) {
		this.cat_attribute3 = cat_attribute3;
	}

	public String getCat_attribute4() {
		return cat_attribute4;
	}

	public void setCat_attribute4(String cat_attribute4) {
		this.cat_attribute4 = cat_attribute4;
	}

	public String getCat_attribute5() {
		return cat_attribute5;
	}

	public void setCat_attribute5(String cat_attribute5) {
		this.cat_attribute5 = cat_attribute5;
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

	public String getCategory_type_category_type() {
		return category_type_category_type;
	}

	public void setCategory_type_category_type(String category_type_category_type) {
		this.category_type_category_type = category_type_category_type;
	}


	

	
}
	