package com.java.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "BalanceSheet")
public class BalanceSheet implements java.io.Serializable {

	@Id
	@Column(name = "year")
	private String year;

	@Column(name = "cash_deposit_central_bank")
	private BigDecimal cash_deposit_central_bank;
	@Column(name = "due_other_bank")
	private BigDecimal due_other_bank;
	@Column(name = "loans_advances_customer")
	private BigDecimal loans_advances_customer;
	@Column(name = "claims_on_soverigns")
	private BigDecimal claims_on_soverigns;
	@Column(name = "pses")
	private BigDecimal pses;
	@Column(name = "claims_securities_firms")
	private BigDecimal claims_securities_firms;
	@Column(name = "claims_corporates")
	private BigDecimal claims_corporates;
	@Column(name = "claims_included_regulatory_retail_portfolio")
	private BigDecimal claims_included_regulatory_retail_portfolio;
	@Column(name = "claims_secured_residential_property")
	private BigDecimal claims_secured_residential_property;
	@Column(name = "claims_secured_commercial_real_estate")
	private BigDecimal claims_secured_commercial_real_estate;
	@Column(name = "past_due_loans")
	private BigDecimal past_due_loans;	
	@Column(name = "less_provisions_basel_return")
	private BigDecimal less_provisions_basel_return;
	@Column(name = "less_general_provision")
	private BigDecimal less_general_provision;
	@Column(name = "diff_allocated_to_unallocated_provision")
	private BigDecimal diff_allocated_to_unallocated_provision;
	@Column(name = "customer_indebtness_acceptances")
	private BigDecimal customer_indebtness_acceptances;
	@Column(name = "investments")
	private BigDecimal investments;
	@Column(name = "sovereign")
	private BigDecimal sovereign;
	@Column(name = "commercial")
	private BigDecimal commercial;
	@Column(name = "property_and_equipment")
	private BigDecimal property_and_equipment;
	@Column(name = "other_assets")
	private BigDecimal other_assets;
	@Column(name = "total_assets")
	private BigDecimal total_assets;
	@Column(name = "due_to_other_banks")
	private BigDecimal due_to_other_banks;
	@Column(name = "due_to_customers")
	private BigDecimal due_to_customers;
	@Column(name = "time_deposit")
	private BigDecimal time_deposit;
	@Column(name = "savings_accounts")
	private BigDecimal savings_accounts;
	@Column(name = "current_and_other_accounts")
	private BigDecimal current_and_other_accounts;
	@Column(name = "liabilities_under_acceptances")
	private BigDecimal liabilities_under_acceptances;
	@Column(name = "other_liabilities")
	private BigDecimal other_liabilities;
	@Column(name = "total_liabilities")
	private BigDecimal total_liabilities;
	@Column(name = "share_capital")
	private BigDecimal share_capital;
	@Column(name = "legal_reserve")
	private BigDecimal legal_reserve;
	@Column(name = "special_reserve")
	private BigDecimal special_reserve;
	@Column(name = "revaluation_reserve")
	private BigDecimal revaluation_reserve;
	@Column(name = "retained_earnings")
	private BigDecimal retained_earnings;
	@Column(name = "total_shareholders_funds")
	private BigDecimal total_shareholders_funds;
	@Column(name = "total_liabilities_shareholders_funds")
	private BigDecimal total_liabilities_shareholders_funds;

	@Column(name = "balancing_node")
	private int balancing_node;
	
	@Column(name = "commitments_contingents_liabilities")
	private BigDecimal commitments_contingents_liabilities;
	
	@Column(name = "xml_data")
	private String xml_data;
	
	public String getXml_data() {
		return xml_data;
	}
	public void setXml_data(String xml_data) {
		this.xml_data = xml_data;
	}
	
/*	@Temporal(TemporalType.DATE)
	@Column(name = "date_created")
	private Date date_created;
	@Column(name = "user_created")
	private String user_created;
	@Column(name = "update_trace")
	private String update_trace;
	
*/	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public BigDecimal getCash_deposit_central_bank() {
		return cash_deposit_central_bank;
	}
	public void setCash_deposit_central_bank(BigDecimal cash_deposit_central_bank) {
		this.cash_deposit_central_bank = cash_deposit_central_bank;
	}
	public BigDecimal getDue_other_bank() {
		return due_other_bank;
	}
	public void setDue_other_bank(BigDecimal due_other_bank) {
		this.due_other_bank = due_other_bank;
	}
	public BigDecimal getLoans_advances_customer() {
		return loans_advances_customer;
	}
	public void setLoans_advances_customer(BigDecimal loans_advances_customer) {
		this.loans_advances_customer = loans_advances_customer;
	}
	public BigDecimal getClaims_on_soverigns() {
		return claims_on_soverigns;
	}
	public void setClaims_on_soverigns(BigDecimal claims_on_soverigns) {
		this.claims_on_soverigns = claims_on_soverigns;
	}
	public BigDecimal getPses() {
		return pses;
	}
	public void setPses(BigDecimal pses) {
		this.pses = pses;
	}
	public BigDecimal getClaims_securities_firms() {
		return claims_securities_firms;
	}
	public void setClaims_securities_firms(BigDecimal claims_securities_firms) {
		this.claims_securities_firms = claims_securities_firms;
	}
	public BigDecimal getClaims_corporates() {
		return claims_corporates;
	}
	public void setClaims_corporates(BigDecimal claims_corporates) {
		this.claims_corporates = claims_corporates;
	}
	public BigDecimal getClaims_included_regulatory_retail_portfolio() {
		return claims_included_regulatory_retail_portfolio;
	}
	public void setClaims_included_regulatory_retail_portfolio(BigDecimal claims_included_regulatory_retail_portfolio) {
		this.claims_included_regulatory_retail_portfolio = claims_included_regulatory_retail_portfolio;
	}
	public BigDecimal getClaims_secured_residential_property() {
		return claims_secured_residential_property;
	}
	public void setClaims_secured_residential_property(BigDecimal claims_secured_residential_property) {
		this.claims_secured_residential_property = claims_secured_residential_property;
	}
	public BigDecimal getClaims_secured_commercial_real_estate() {
		return claims_secured_commercial_real_estate;
	}
	public void setClaims_secured_commercial_real_estate(BigDecimal claims_secured_commercial_real_estate) {
		this.claims_secured_commercial_real_estate = claims_secured_commercial_real_estate;
	}
	public BigDecimal getPast_due_loans() {
		return past_due_loans;
	}
	public void setPast_due_loans(BigDecimal past_due_loans) {
		this.past_due_loans = past_due_loans;
	}
	public BigDecimal getLess_provisions_basel_return() {
		return less_provisions_basel_return;
	}
	public void setLess_provisions_basel_return(BigDecimal less_provisions_basel_return) {
		this.less_provisions_basel_return = less_provisions_basel_return;
	}
	public BigDecimal getLess_general_provision() {
		return less_general_provision;
	}
	public void setLess_general_provision(BigDecimal less_general_provision) {
		this.less_general_provision = less_general_provision;
	}
	public BigDecimal getDiff_allocated_to_unallocated_provision() {
		return diff_allocated_to_unallocated_provision;
	}
	public void setDiff_allocated_to_unallocated_provision(BigDecimal diff_allocated_to_unallocated_provision) {
		this.diff_allocated_to_unallocated_provision = diff_allocated_to_unallocated_provision;
	}
	public BigDecimal getCustomer_indebtness_acceptances() {
		return customer_indebtness_acceptances;
	}
	public void setCustomer_indebtness_acceptances(BigDecimal customer_indebtness_acceptances) {
		this.customer_indebtness_acceptances = customer_indebtness_acceptances;
	}
	public BigDecimal getInvestments() {
		return investments;
	}
	public void setInvestments(BigDecimal investments) {
		this.investments = investments;
	}
	public BigDecimal getSovereign() {
		return sovereign;
	}
	public void setSovereign(BigDecimal sovereign) {
		this.sovereign = sovereign;
	}
	public BigDecimal getCommercial() {
		return commercial;
	}
	public void setCommercial(BigDecimal commercial) {
		this.commercial = commercial;
	}
	public BigDecimal getProperty_and_equipment() {
		return property_and_equipment;
	}
	public void setProperty_and_equipment(BigDecimal property_and_equipment) {
		this.property_and_equipment = property_and_equipment;
	}
	public BigDecimal getOther_assets() {
		return other_assets;
	}
	public void setOther_assets(BigDecimal other_assets) {
		this.other_assets = other_assets;
	}
	public BigDecimal getTotal_assets() {
		return total_assets;
	}
	public void setTotal_assets(BigDecimal total_assets) {
		this.total_assets = total_assets;
	}
	public BigDecimal getDue_to_other_banks() {
		return due_to_other_banks;
	}
	public void setDue_to_other_banks(BigDecimal due_to_other_banks) {
		this.due_to_other_banks = due_to_other_banks;
	}
	public BigDecimal getDue_to_customers() {
		return due_to_customers;
	}
	public void setDue_to_customers(BigDecimal due_to_customers) {
		this.due_to_customers = due_to_customers;
	}
	public BigDecimal getTime_deposit() {
		return time_deposit;
	}
	public void setTime_deposit(BigDecimal time_deposit) {
		this.time_deposit = time_deposit;
	}
	public BigDecimal getSavings_accounts() {
		return savings_accounts;
	}
	public void setSavings_accounts(BigDecimal savings_accounts) {
		this.savings_accounts = savings_accounts;
	}
	public BigDecimal getCurrent_and_other_accounts() {
		return current_and_other_accounts;
	}
	public void setCurrent_and_other_accounts(BigDecimal current_and_other_accounts) {
		this.current_and_other_accounts = current_and_other_accounts;
	}
	public BigDecimal getLiabilities_under_acceptances() {
		return liabilities_under_acceptances;
	}
	public void setLiabilities_under_acceptances(BigDecimal liabilities_under_acceptances) {
		this.liabilities_under_acceptances = liabilities_under_acceptances;
	}
	public BigDecimal getOther_liabilities() {
		return other_liabilities;
	}
	public void setOther_liabilities(BigDecimal other_liabilities) {
		this.other_liabilities = other_liabilities;
	}
	public BigDecimal getTotal_liabilities() {
		return total_liabilities;
	}
	public void setTotal_liabilities(BigDecimal total_liabilities) {
		this.total_liabilities = total_liabilities;
	}
	public BigDecimal getShare_capital() {
		return share_capital;
	}
	public void setShare_capital(BigDecimal share_capital) {
		this.share_capital = share_capital;
	}
	public BigDecimal getLegal_reserve() {
		return legal_reserve;
	}
	public void setLegal_reserve(BigDecimal legal_reserve) {
		this.legal_reserve = legal_reserve;
	}
	public BigDecimal getSpecial_reserve() {
		return special_reserve;
	}
	public void setSpecial_reserve(BigDecimal special_reserve) {
		this.special_reserve = special_reserve;
	}
	public BigDecimal getRevaluation_reserve() {
		return revaluation_reserve;
	}
	public void setRevaluation_reserve(BigDecimal revaluation_reserve) {
		this.revaluation_reserve = revaluation_reserve;
	}
	public BigDecimal getRetained_earnings() {
		return retained_earnings;
	}
	public void setRetained_earnings(BigDecimal retained_earnings) {
		this.retained_earnings = retained_earnings;
	}
	public BigDecimal getTotal_shareholders_funds() {
		return total_shareholders_funds;
	}
	public void setTotal_shareholders_funds(BigDecimal total_shareholders_funds) {
		this.total_shareholders_funds = total_shareholders_funds;
	}
	public BigDecimal getTotal_liabilities_shareholders_funds() {
		return total_liabilities_shareholders_funds;
	}
	public void setTotal_liabilities_shareholders_funds(BigDecimal total_liabilities_shareholders_funds) {
		this.total_liabilities_shareholders_funds = total_liabilities_shareholders_funds;
	}
	/*public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public String getUser_created() {
		return user_created;
	}
	public void setUser_created(String user_created) {
		this.user_created = user_created;
	}
	public String getUpdate_trace() {
		return update_trace;
	}
	public void setUpdate_trace(String update_trace) {
		this.update_trace = update_trace;
	}
*/
	public int getBalancing_node() {
		return balancing_node;
	}
	public void setBalancing_node(int balancing_node) {
		this.balancing_node = balancing_node;
	}
	public BigDecimal getCommitments_contingents_liabilities() {
		return commitments_contingents_liabilities;
	}
	public void setCommitments_contingents_liabilities(BigDecimal commitments_contingents_liabilities) {
		this.commitments_contingents_liabilities = commitments_contingents_liabilities;
	}	
	}
