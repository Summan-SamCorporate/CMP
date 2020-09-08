package com.java.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;



@XmlRootElement(name = "item")
@XmlAccessorType (XmlAccessType.FIELD)
public class Item{
	private boolean visibility;
	 private String default_value;
	 private String customised_value;
	 private String url;
	 
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	public String getCustomised_value() {
		return customised_value;
	}
	public void setCustomised_value(String customised_value) {
		this.customised_value = customised_value;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
