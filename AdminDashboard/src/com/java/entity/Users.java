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


/*import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
*/

// Changed by Bijoy


@Entity
@Table(name = "USERS")

public class Users {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name; 

	@OneToOne
	private Roles role;

	@OneToOne
	private UserStatus status;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "entity")
	private String entity;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "designation")
	private String designation;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_login_date")
	private Date last_login_date;
	
	@Column(name = "cod_pwd_rule")
	private String cod_pwd_rule;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "password_change_date")
	private Date password_change_date;
	
	@Column(name = "no_of_failure")
	private int no_of_failure;	
	
	@Column(name="session_time_out")
	private int session_time_out;
	
	@Column(name = "flag_password_admin")
	private String flag_password_admin;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	@Column(name = "updated_user")
	private String updated_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "column_status")
	private String column_status;

	@Column(name="stylesheet_name")
	private String stylesheet_name;
	
	public String getStylesheet_name() {
		return stylesheet_name;
	}


	public Users(String name, String pass) {
		// TODO Auto-generated constructor stub
		username = name;
		password = pass;
	}

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getCod_pwd_rule() {
		return cod_pwd_rule;
	}

	public void setCod_pwd_rule(String cod_pwd_rule) {
		this.cod_pwd_rule = cod_pwd_rule;
	}

	public Date getPassword_change_date() {
		return password_change_date;
	}

	public void setPassword_change_date(Date password_change_date) {
		this.password_change_date = password_change_date;
	}

	public int getNo_of_failure() {
		return no_of_failure;
	}

	public void setNo_of_failure(int no_of_failure) {
		this.no_of_failure = no_of_failure;
	}

	public String getFlag_password_admin() {
		return flag_password_admin;
	}

	public void setFlag_password_admin(String flag_password_admin) {
		this.flag_password_admin = flag_password_admin;
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

	public String getUpdated_user() {
		return updated_user;
	}

	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
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

	public int getSession_time_out() {
		return session_time_out;
	}


	public void setSession_time_out(int session_time_out) {
		this.session_time_out = session_time_out;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setStylesheet_name(String stylesheet_name) {
		this.stylesheet_name = stylesheet_name;
	}
	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
}