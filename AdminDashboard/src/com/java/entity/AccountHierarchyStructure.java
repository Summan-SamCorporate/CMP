package com.java.entity;
/*
 * Created By: Summan Bahadur
 * Description: Tree Structure for Account Hierarchy saved in a flat database table
 * 
 */
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
@Table(name = "_DIM_ACCOUNT_HIERARCHY_STRUCTURE")

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHierarchyStructure {

	private static final long serialVersionUID = 1L;
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;*/
	
	@Column(name = "account_hierarchy_code")
	private String account_hierarchy_code;
	
	@Id
	@Column(name = "node")
	private String node;
	
	@Column(name = "node_description")
	private String node_description;
	
	@Column(name = "parent")
	private String parent;
	
	@Column(name = "parent_description")
	private String parent_description;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "node_type")
	private String node_type;
	
	
	@Column(name = "application_no")
	private String application_no;

	@Column(name = "attribute1")
	private String attribute1;
	
	@Column(name = "attribute2")
	private String attribute2;
		
	@Column(name = "attribute3")
	private String attribute3;
	
	@Column(name = "attribute4")
	private String attribute4;
	
	@Column(name = "attribute5")
	private String attribute5;
	
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

/*	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
*/
	public String getAccount_hierarchy_code() {
		return account_hierarchy_code;
	}

	public void setAccount_hierarchy_code(String account_hierarchy_code) {
		this.account_hierarchy_code = account_hierarchy_code;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getNode_description() {
		return node_description;
	}

	public void setNode_description(String node_description) {
		this.node_description = node_description;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getParent_description() {
		return parent_description;
	}

	public void setParent_description(String parent_description) {
		this.parent_description = parent_description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
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
	