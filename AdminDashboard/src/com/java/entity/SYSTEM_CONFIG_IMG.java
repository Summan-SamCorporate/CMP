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
@Table(name = "SYSTEM_CONFIG_IMG")

public class SYSTEM_CONFIG_IMG {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "IMAGE_DATA")
	private  byte[] image_data;
	
	@Column(name = "IMAGE_FILE_NAME")
	private String image_file_name;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte[] getImage_data() {
		return image_data;
	}


	public void setImage_data(byte[] image_data) {
		this.image_data = image_data;
	}


	public String getImage_file_name() {
		return image_file_name;
	}


	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}


	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
	