package com.java.bean;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "items")
@XmlAccessorType (XmlAccessType.FIELD)
public class Items{
	 @XmlElement(name = "items")
	    private List<Item> items = null;
	 
	    public List<Item> getItems() {
	        return items;
	    }
	 
	    public void setItems(List<Item> arrayList) {
	        this.items = arrayList;
	    }	
}
