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
@Table(name = "ratios")
public class Ratios implements java.io.Serializable {

	
	
	@Id
	@Column(name = "year")
	private String year;

	@Column(name = "capital_adequacy_ratio")
	private BigDecimal capital_adequacy_ratio;
	
	@Column(name = "expected_la_to_customers")
	private BigDecimal expected_la_to_customers;
	
	@Column(name = "general_and_admn_exp_TA")
	private BigDecimal general_and_admn_exp_TA;
	
	@Column(name = "interest_income_int_earning_assets")
	private BigDecimal interest_income_int_earning_assets;
	
	@Column(name = "Interest_exp_int_bearing_liabilities")
	private BigDecimal Interest_exp_int_bearing_liabilities;
	
	@Column(name = "Comm_fees_ol_claims_corp")
	private BigDecimal Comm_fees_ol_claims_corp;
	
	@Column(name = "Dtb_dfb")
	private BigDecimal Dtb_dfb;
	
	@Column(name = "advance_deposit_ratio")
	private BigDecimal advance_deposit_ratio;

	public BigDecimal getAdvance_deposit_ratio() {
		return advance_deposit_ratio;
	}

	public void setAdvance_deposit_ratio(BigDecimal advance_deposit_ratio) {
		this.advance_deposit_ratio = advance_deposit_ratio;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getCapital_adequacy_ratio() {
		return capital_adequacy_ratio;
	}

	public void setCapital_adequacy_ratio(BigDecimal capital_adequacy_ratio) {
		this.capital_adequacy_ratio = capital_adequacy_ratio;
	}

	public BigDecimal getExpected_la_to_customers() {
		return expected_la_to_customers;
	}

	public void setExpected_la_to_customers(BigDecimal expected_la_to_customers) {
		this.expected_la_to_customers = expected_la_to_customers;
	}

	public BigDecimal getGeneral_and_admn_exp_TA() {
		return general_and_admn_exp_TA;
	}

	public void setGeneral_and_admn_exp_TA(BigDecimal general_and_admn_exp_TA) {
		this.general_and_admn_exp_TA = general_and_admn_exp_TA;
	}

	public BigDecimal getInterest_income_int_earning_assets() {
		return interest_income_int_earning_assets;
	}

	public void setInterest_income_int_earning_assets(BigDecimal interest_income_int_earning_assets) {
		this.interest_income_int_earning_assets = interest_income_int_earning_assets;
	}

	public BigDecimal getInterest_exp_int_bearing_liabilities() {
		return Interest_exp_int_bearing_liabilities;
	}

	public void setInterest_exp_int_bearing_liabilities(BigDecimal interest_exp_int_bearing_liabilities) {
		Interest_exp_int_bearing_liabilities = interest_exp_int_bearing_liabilities;
	}

	public BigDecimal getComm_fees_ol_claims_corp() {
		return Comm_fees_ol_claims_corp;
	}

	public void setComm_fees_ol_claims_corp(BigDecimal comm_fees_ol_claims_corp) {
		Comm_fees_ol_claims_corp = comm_fees_ol_claims_corp;
	}

	public BigDecimal getDtb_dfb() {
		return Dtb_dfb;
	}

	public void setDtb_dfb(BigDecimal dtb_dfb) {
		Dtb_dfb = dtb_dfb;
	}
	}
