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
@Table(name = "Loading")

public class Loading {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "loading_code")
	private String loading_code;
	
	@Column(name = "container_code")
	private String container_code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "entity")
	private String entity;
	@Column(name = "account")
	private String account;
	
	@Column(name = "custom_dim_1")
	private String custom_dim_1;	
	@Column(name = "custom_dim_2")
	private String custom_dim_2;	
	@Column(name = "custom_dim_3")
	private String custom_dim_3;
	@Column(name = "custom_dim_4")
	private String custom_dim_4;
	@Column(name = "custom_dim_5")
	private String custom_dim_5;
	@Column(name = "custom_dim_6")
	private String custom_dim_6;
	@Column(name = "custom_dim_7")
	private String custom_dim_7;
	@Column(name = "custom_dim_8")
	private String custom_dim_8;
	@Column(name = "custom_dim_9")
	private String custom_dim_9;
	@Column(name = "custom_dim_10")
	private String custom_dim_10;
	@Column(name = "custom_dim_11")
	private String custom_dim_11;
	@Column(name = "custom_dim_12")
	private String custom_dim_12;
	
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

	public String getLoading_code() {
		return loading_code;
	}

	public void setLoading_code(String loading_code) {
		this.loading_code = loading_code;
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

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCustom_dim_1() {
		return custom_dim_1;
	}

	public void setCustom_dim_1(String custom_dim_1) {
		this.custom_dim_1 = custom_dim_1;
	}

	public String getCustom_dim_2() {
		return custom_dim_2;
	}

	public void setCustom_dim_2(String custom_dim_2) {
		this.custom_dim_2 = custom_dim_2;
	}

	public String getCustom_dim_3() {
		return custom_dim_3;
	}

	public void setCustom_dim_3(String custom_dim_3) {
		this.custom_dim_3 = custom_dim_3;
	}

	public String getCustom_dim_4() {
		return custom_dim_4;
	}

	public void setCustom_dim_4(String custom_dim_4) {
		this.custom_dim_4 = custom_dim_4;
	}

	public String getCustom_dim_5() {
		return custom_dim_5;
	}

	public void setCustom_dim_5(String custom_dim_5) {
		this.custom_dim_5 = custom_dim_5;
	}

	public String getCustom_dim_6() {
		return custom_dim_6;
	}

	public void setCustom_dim_6(String custom_dim_6) {
		this.custom_dim_6 = custom_dim_6;
	}

	public String getCustom_dim_7() {
		return custom_dim_7;
	}

	public void setCustom_dim_7(String custom_dim_7) {
		this.custom_dim_7 = custom_dim_7;
	}

	public String getCustom_dim_8() {
		return custom_dim_8;
	}

	public void setCustom_dim_8(String custom_dim_8) {
		this.custom_dim_8 = custom_dim_8;
	}

	public String getCustom_dim_9() {
		return custom_dim_9;
	}

	public void setCustom_dim_9(String custom_dim_9) {
		this.custom_dim_9 = custom_dim_9;
	}

	public String getCustom_dim_10() {
		return custom_dim_10;
	}

	public void setCustom_dim_10(String custom_dim_10) {
		this.custom_dim_10 = custom_dim_10;
	}

	public String getCustom_dim_11() {
		return custom_dim_11;
	}

	public void setCustom_dim_11(String custom_dim_11) {
		this.custom_dim_11 = custom_dim_11;
	}

	public String getCustom_dim_12() {
		return custom_dim_12;
	}

	public void setCustom_dim_12(String custom_dim_12) {
		this.custom_dim_12 = custom_dim_12;
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
	