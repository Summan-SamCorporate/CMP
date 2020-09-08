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
@Table(name = "System_Config_Tiles")

public class System_Config_Tiles {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "DISPLAY_NAME")
	private String display_name;
	
	@Column(name = "ICON")
	private  byte[] icon;
	
	@Column(name = "ICON_FILE_NAME")
	private String icon_file_name;
	
	@Column(name = "BACKGROUND_COLOR")
	private String background_color;
	
	@Column(name = "URL")
	private String url;
	

	@Column(name = "column_status")
	private String column_status;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public String getIcon_file_name() {
		return icon_file_name;
	}
	public void setIcon_file_name(String icon_file_name) {
		this.icon_file_name = icon_file_name;
	}
	public String getBackground_color() {
		return background_color;
	}
	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getColumn_status() {
		return column_status;
	}
	public void setColumn_status(String column_status) {
		this.column_status = column_status;
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
}
	