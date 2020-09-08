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
@Table(name = "Transformation")

public class Transformation {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transformation_code")
	private String transformation_code;
	
	@Column(name = "container_code")
	private String container_code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "column1")
	private String column1;
	@Column(name = "column2")
	private String column2;
	@Column(name = "column3")
	private String column3;
	@Column(name = "column4")
	private String column4;
	@Column(name = "column5")
	private String column5;
	@Column(name = "column6")
	private String column6;
	@Column(name = "column7")
	private String column7;
	@Column(name = "column8")
	private String column8;
	@Column(name = "column9")
	private String column9;
	@Column(name = "column10")
	private String column10;
	
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

	public String getTransformation_code() {
		return transformation_code;
	}

	public void setTransformation_code(String transformation_code) {
		this.transformation_code = transformation_code;
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

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
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
	