package com.java.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Class created for TreeStore objects in EXTJS ;ScenarioHierarchy

public class AccountTreeNode{
	
	//int id;
	String label;
	String account_hierarchy_code;
	String node;
	String node_description;
	String parent;
	String parent_description;
	String node_type;	
	String attribute1;
	String attribute2;
	String attribute3;
	String attribut4;
	String attibute5;
	String application_no;
	Date created_date;
	Date updated_date;
	String created_user;
	String column_status;
	
	//Variable specific to display tree
	String text;
	Boolean leaf;
	Boolean expanded;
	
	List<AccountTreeNode> children = new ArrayList<AccountTreeNode>();
	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
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
	public String getNode_type() {
		return node_type;
	}
	public void setNode_type(String node_type) {
		this.node_type = node_type;
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
	public String getAttribut4() {
		return attribut4;
	}
	public void setAttribut4(String attribut4) {
		this.attribut4 = attribut4;
	}
	public String getAttibute5() {
		return attibute5;
	}
	public void setAttibute5(String attibute5) {
		this.attibute5 = attibute5;
	}
	public String getApplication_no() {
		return application_no;
	}
	public void setApplication_no(String application_no) {
		this.application_no = application_no;
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
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getColumn_status() {
		return column_status;
	}
	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public List<AccountTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<AccountTreeNode> children) {
		this.children = children;
	}
	

}