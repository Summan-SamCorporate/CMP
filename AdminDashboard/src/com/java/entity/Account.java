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
@Table(name = "_DIM_ACCOUNT")

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_code")
	private String account_code;
	
	@Column(name = "application_no")
	private String application_no;

	@Column(name = "account_description")
	private String account_description;

	@Column(name = "alternate_description")
	private String alternate_description;


	@OneToOne( fetch=FetchType.EAGER)
	 @JoinColumn(name = "label_code")
	private Label label;
	
	@OneToOne( fetch=FetchType.EAGER)
	 @JoinColumn(name = "nature_code")
	private Nature nature;

	@OneToOne( fetch=FetchType.EAGER)
	@JoinColumn(name = "type_code")
	private Type type;
	
	@Column(name = "account_attr1")
	private String account_attr1;
	
	@Column(name = "account_attr2")
	private String account_attr2;
		
	@Column(name = "account_attr3")
	private String account_attr3;
	
	@Column(name = "account_attr4")
	private String account_attr4;
	
	@Column(name = "account_attr5")
	private String account_attr5;
	
	@Column(name = "account_attr6")
	private String account_attr6;
	
	@Column(name = "account_attr7")
	private String account_attr7;
	
	@Column(name = "account_attr8")
	private String account_attr8;
	
	@Column(name = "account_attr9")
	private String account_attr9;
	
	@Column(name = "account_attr10")
	private String account_attr10;
	
	@Column(name = "account_attr11")
	private String account_attr11;
	
	@Column(name = "account_attr12")
	private String account_attr12;
	
	@Column(name = "account_attr13")
	private String account_attr13;
	
	@Column(name = "account_attr14")
	private String account_attr14;
	
	@Column(name = "account_attr15")
	private String account_attr15;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;
 
	@Column(name = "account_status")
	private String account_status;

	@Column(name = "default_flag")
	private String default_flag;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccount_code() {
		return account_code;
	}

	public void setAccount_code(String account_code) {
		this.account_code = account_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getAccount_description() {
		return account_description;
	}

	public void setAccount_description(String account_description) {
		this.account_description = account_description;
	}

	public String getAlternate_description() {
		return alternate_description;
	}

	public void setAlternate_description(String alternate_description) {
		this.alternate_description = alternate_description;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getAccount_attr1() {
		return account_attr1;
	}

	public void setAccount_attr1(String account_attr1) {
		this.account_attr1 = account_attr1;
	}

	public String getAccount_attr2() {
		return account_attr2;
	}

	public void setAccount_attr2(String account_attr2) {
		this.account_attr2 = account_attr2;
	}

	public String getAccount_attr3() {
		return account_attr3;
	}

	public void setAccount_attr3(String account_attr3) {
		this.account_attr3 = account_attr3;
	}

	public String getAccount_attr4() {
		return account_attr4;
	}

	public void setAccount_attr4(String account_attr4) {
		this.account_attr4 = account_attr4;
	}

	public String getAccount_attr5() {
		return account_attr5;
	}

	public void setAccount_attr5(String account_attr5) {
		this.account_attr5 = account_attr5;
	}

	public String getAccount_attr6() {
		return account_attr6;
	}

	public void setAccount_attr6(String account_attr6) {
		this.account_attr6 = account_attr6;
	}

	public String getAccount_attr7() {
		return account_attr7;
	}

	public void setAccount_attr7(String account_attr7) {
		this.account_attr7 = account_attr7;
	}

	public String getAccount_attr8() {
		return account_attr8;
	}

	public void setAccount_attr8(String account_attr8) {
		this.account_attr8 = account_attr8;
	}

	public String getAccount_attr9() {
		return account_attr9;
	}

	public void setAccount_attr9(String account_attr9) {
		this.account_attr9 = account_attr9;
	}

	public String getAccount_attr10() {
		return account_attr10;
	}

	public void setAccount_attr10(String account_attr10) {
		this.account_attr10 = account_attr10;
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

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	public String getDefault_flag() {
		return default_flag;
	}

	public void setDefault_flag(String default_flag) {
		this.default_flag = default_flag;
	}



	public String getAccount_attr11() {
		return account_attr11;
	}

	public void setAccount_attr11(String account_attr11) {
		this.account_attr11 = account_attr11;
	}

	public String getAccount_attr12() {
		return account_attr12;
	}

	public void setAccount_attr12(String account_attr12) {
		this.account_attr12 = account_attr12;
	}

	public String getAccount_attr13() {
		return account_attr13;
	}

	public void setAccount_attr13(String account_attr13) {
		this.account_attr13 = account_attr13;
	}

	public String getAccount_attr14() {
		return account_attr14;
	}

	public void setAccount_attr14(String account_attr14) {
		this.account_attr14 = account_attr14;
	}

	public String getAccount_attr15() {
		return account_attr15;
	}

	public void setAccount_attr15(String account_attr15) {
		this.account_attr15 = account_attr15;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}



	
}
	