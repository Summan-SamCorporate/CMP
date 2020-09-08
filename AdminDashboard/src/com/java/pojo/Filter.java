package com.java.pojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Filter {

	private String operator ;
	private List<String> value;
	private String property;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/*public String[] getValue() {
		return value;
	}
	public void setValue(String value[]) {
		this.value = value;
	}*/
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	}
