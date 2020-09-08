package com.java.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.MapKey;
import org.hibernate.mapping.Map;

import java.util.Date;
import java.util.HashMap;

@Entity
@Table(name = "IncomeStatements")
public class IncomeStatements implements java.io.Serializable {

	//Requirement EI_Upload
	//I : Input , EI : expert input , A : Adjusted
	
	@Id
	@Column(name = "year")
	private String year;
	
/*	@Id
	@Column(name = "type")
	private String type;
*/
	@Column(name = "net_interest_income")
	private BigDecimal net_interest_income;
	
	@Column(name = "interest_income")
	private BigDecimal interest_income;
/*	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
*/	@Column(name = "less_interest_expenses")
	private BigDecimal less_interest_expenses;
	@Column(name = "commission_fees_other_income")
	private BigDecimal commission_fees_other_income;
	@Column(name = "foreign_exchange")
	private BigDecimal foreign_exchange;
	@Column(name = "operating_income")
	private BigDecimal operating_income;
	@Column(name = "general_admin_expenses")
	private BigDecimal general_admin_expenses;
	@Column(name = "net_impaired_losses")
	private BigDecimal net_impaired_losses;
	@Column(name = "general_provisions")
	private BigDecimal general_provisions;
	@Column(name = "other_provision_for_expenses")
	private BigDecimal other_provision_for_expenses;
	@Column(name = "net_profit_year")
	private BigDecimal net_profit_year;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_created")
	private Date date_created;
	@Column(name = "user_created")
	private Date user_created;
	@Column(name = "update_trace")
	private Date update_trace;

	@Column(name = "xml_data")
	private String xml_data;
	
	public String getXml_data() {
		return xml_data;
	}
	public void setXml_data(String xml_data) {
		this.xml_data = xml_data;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public BigDecimal getNet_interest_income() {
		return net_interest_income;
	}
	public void setNet_interest_income(BigDecimal net_interest_income) {
		this.net_interest_income = net_interest_income;
	}
	public BigDecimal getInterest_income() {
		return interest_income;
	}
	public void setInterest_income(BigDecimal interest_income) {
		this.interest_income = interest_income;
	}
	public BigDecimal getLess_interest_expenses() {
		return less_interest_expenses;
	}
	public void setLess_interest_expenses(BigDecimal less_interest_expenses) {
		this.less_interest_expenses = less_interest_expenses;
	}
	public BigDecimal getCommission_fees_other_income() {
		return commission_fees_other_income;
	}
	public void setCommission_fees_other_income(BigDecimal commission_fees_other_income) {
		this.commission_fees_other_income = commission_fees_other_income;
	}
	public BigDecimal getForeign_exchange() {
		return foreign_exchange;
	}
	public void setForeign_exchange(BigDecimal foreign_exchange) {
		this.foreign_exchange = foreign_exchange;
	}
	public BigDecimal getOperating_income() {
		return operating_income;
	}
	public void setOperating_income(BigDecimal operating_income) {
		this.operating_income = operating_income;
	}
	public BigDecimal getGeneral_admin_expenses() {
		return general_admin_expenses;
	}
	public void setGeneral_admin_expenses(BigDecimal general_admin_expenses) {
		this.general_admin_expenses = general_admin_expenses;
	}
	public BigDecimal getNet_impaired_losses() {
		return net_impaired_losses;
	}
	public void setNet_impaired_losses(BigDecimal net_impaired_losses) {
		this.net_impaired_losses = net_impaired_losses;
	}
	public BigDecimal getGeneral_provisions() {
		return general_provisions;
	}
	public void setGeneral_provisions(BigDecimal general_provisions) {
		this.general_provisions = general_provisions;
	}
	public BigDecimal getOther_provision_for_expenses() {
		return other_provision_for_expenses;
	}
	public void setOther_provision_for_expenses(BigDecimal other_provision_for_expenses) {
		this.other_provision_for_expenses = other_provision_for_expenses;
	}
	public BigDecimal getNet_profit_year() {
		return net_profit_year;
	}
	public void setNet_profit_year(BigDecimal net_profit_year) {
		this.net_profit_year = net_profit_year;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public Date getUser_created() {
		return user_created;
	}
	public void setUser_created(Date user_created) {
		this.user_created = user_created;
	}
	public Date getUpdate_trace() {
		return update_trace;
	}
	public void setUpdate_trace(Date update_trace) {
		this.update_trace = update_trace;
	}
	
	
	
	
	

}
