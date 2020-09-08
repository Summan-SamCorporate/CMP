package com.java.entity;

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



@Entity
@Table(name = "SCENARIO_TREE_NODE")

public class Scenario_Tree_Node  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "node_code")
	private String node_code;
	
	@Column(name = "text")
	private String text; 

	@Column(name="next_node")
	private String next_node;
	
	@Column(name="previous_node")
	private String previous_node;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	
	@Column(name = "APPLICATION_NO")
	private String application_no;

	public String getNode_code() {
		return node_code;
	}

	public void setNode_code(String node_code) {
		this.node_code = node_code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNext_node() {
		return next_node;
	}

	public void setNext_node(String next_node) {
		this.next_node = next_node;
	}

	public String getPrevious_node() {
		return previous_node;
	}

	public void setPrevious_node(String previous_node) {
		this.previous_node = previous_node;
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


	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}
	