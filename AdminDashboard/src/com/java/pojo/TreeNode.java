package com.java.pojo;

import java.util.ArrayList;
import java.util.List;

// Class created for TreeStore objects in EXTJS ;ScenarioHierarchy

public class TreeNode{
	
	int id;
	String text;
	Boolean leaf;
	Boolean expanded;
	List<TreeNode> children = new ArrayList<TreeNode>();
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}