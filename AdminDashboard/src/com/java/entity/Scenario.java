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
@Table(name = "_DIM_SCENARIO")

//Saving Objects inside an object gives cyclic dependency exception while converting to JSON when fetching from database
//Hence, Save objects as a REFERENCE instead of creating foriegn keys

@JsonIgnoreProperties(ignoreUnknown = true)
public class Scenario  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SCENARIO_CODE")
	private String scenario_code;
	
	@Column(name = "APPLICATION_NO")
	private String application_no;

	@Column(name = "SCENARIO_DESCRIPTION")
	private String scenario_description; 

	@OneToOne( fetch=FetchType.EAGER)
	 @JoinColumn(name = "version_code")
	private Scenario_Version version;

	@Column(name="YEAR")
	private String year;

	@Column(name="previous_scenario")
	private String previous_scenario;
	
	@Column(name="previous_scenario_desc")
	private String previous_scenario_desc;
	
	@Column(name = "next_scenario")
	private String next_scenario;
		
	@Column(name = "next_scenario_desc")
	private String next_scenario_desc;
	
	@Column(name = "ref_scenario1")
	private String ref_scenario1;
	
	@Column(name = "ref_scenario1_desc")
	private String ref_scenario1_desc;
		
	@Column(name = "ref_scenario2")
	private String ref_scenario2;
	
	@Column(name = "ref_scenario2_desc")
	private String ref_scenario2_desc;
		
	@Column(name = "ref_scenario3")
	private String ref_scenario3;
	
	@Column(name = "ref_scenario3_desc")
	private String ref_scenario3_desc;
	
	@Column(name = "ref_scenario4")
	private String ref_scenario4;
	
	@Column(name = "ref_scenario4_desc")
	private String ref_scenario4_desc;
	
	@Column(name = "ref_scenario5")
	private String ref_scenario5;
	
	@Column(name = "ref_scenario5_desc")
	private String ref_scenario5_desc;
	
	@Column(name = "ref_scenario6")
	private String ref_scenario6;
	
	@Column(name = "ref_scenario6_desc")
	private String ref_scenario6_desc;
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "scenario_status")
	private String scenario_status;
	
	//Scenario Hierarchy
	@Column(name="tree_sub_node")
	private String tree_sub_node;
	
	//------Many to Many relation with Period---------------//
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Scenario_Period", 
        joinColumns = { @JoinColumn(name = "SCENARIO_CODE") }, 
        inverseJoinColumns = { @JoinColumn(name = "period_code") }
    )
    Set<Period> periods = new HashSet<>();


	public Set<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(Set<Period> periods) {
		this.periods = periods;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getScenario_code() {
		return scenario_code;
	}

	public void setScenario_code(String scenario_code) {
		this.scenario_code = scenario_code;
	}

	public String getApplication_no() {
		return application_no;
	}

	public void setApplication_no(String application_no) {
		this.application_no = application_no;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrevious_scenario() {
		return previous_scenario;
	}

	public void setPrevious_scenario(String previous_scenario) {
		this.previous_scenario = previous_scenario;
	}

	public String getPrevious_scenario_desc() {
		return previous_scenario_desc;
	}

	public void setPrevious_scenario_desc(String previous_scenario_desc) {
		this.previous_scenario_desc = previous_scenario_desc;
	}

	public String getNext_scenario() {
		return next_scenario;
	}

	public void setNext_scenario(String next_scenario) {
		this.next_scenario = next_scenario;
	}

	public String getNext_scenario_desc() {
		return next_scenario_desc;
	}

	public void setNext_scenario_desc(String next_scenario_desc) {
		this.next_scenario_desc = next_scenario_desc;
	}

	public String getRef_scenario1() {
		return ref_scenario1;
	}

	public void setRef_scenario1(String ref_scenario1) {
		this.ref_scenario1 = ref_scenario1;
	}

	public String getRef_scenario1_desc() {
		return ref_scenario1_desc;
	}

	public void setRef_scenario1_desc(String ref_scenario1_desc) {
		this.ref_scenario1_desc = ref_scenario1_desc;
	}

	public String getRef_scenario2() {
		return ref_scenario2;
	}

	public void setRef_scenario2(String ref_scenario2) {
		this.ref_scenario2 = ref_scenario2;
	}

	public String getRef_scenario2_desc() {
		return ref_scenario2_desc;
	}

	public void setRef_scenario2_desc(String ref_scenario2_desc) {
		this.ref_scenario2_desc = ref_scenario2_desc;
	}

	public String getRef_scenario3() {
		return ref_scenario3;
	}

	public void setRef_scenario3(String ref_scenario3) {
		this.ref_scenario3 = ref_scenario3;
	}

	public String getRef_scenario3_desc() {
		return ref_scenario3_desc;
	}

	public void setRef_scenario3_desc(String ref_scenario3_desc) {
		this.ref_scenario3_desc = ref_scenario3_desc;
	}

	public String getRef_scenario4() {
		return ref_scenario4;
	}

	public void setRef_scenario4(String ref_scenario4) {
		this.ref_scenario4 = ref_scenario4;
	}

	public String getRef_scenario4_desc() {
		return ref_scenario4_desc;
	}

	public void setRef_scenario4_desc(String ref_scenario4_desc) {
		this.ref_scenario4_desc = ref_scenario4_desc;
	}

	public String getRef_scenario5() {
		return ref_scenario5;
	}

	public void setRef_scenario5(String ref_scenario5) {
		this.ref_scenario5 = ref_scenario5;
	}

	public String getRef_scenario5_desc() {
		return ref_scenario5_desc;
	}

	public void setRef_scenario5_desc(String ref_scenario5_desc) {
		this.ref_scenario5_desc = ref_scenario5_desc;
	}

	public String getRef_scenario6() {
		return ref_scenario6;
	}

	public void setRef_scenario6(String ref_scenario6) {
		this.ref_scenario6 = ref_scenario6;
	}

	public String getRef_scenario6_desc() {
		return ref_scenario6_desc;
	}

	public void setRef_scenario6_desc(String ref_scenario6_desc) {
		this.ref_scenario6_desc = ref_scenario6_desc;
	}

	public String getScenario_description() {
		return scenario_description;
	}

	public void setScenario_description(String scenario_description) {
		this.scenario_description = scenario_description;
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

	public String getScenario_status() {
		return scenario_status;
	}

	public void setScenario_status(String scenario_status) {
		this.scenario_status = scenario_status;
	}

	public String getTree_sub_node() {
		return tree_sub_node;
	}

	public void setTree_sub_node(String tree_sub_node) {
		this.tree_sub_node = tree_sub_node;
	}

	public Scenario_Version getVersion() {
		return version;
	}

	public void setVersion(Scenario_Version version) {
		this.version = version;
	}

	
}
	