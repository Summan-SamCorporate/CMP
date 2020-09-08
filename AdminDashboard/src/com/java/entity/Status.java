package com.java.entity;

public enum Status {
     SUCCESS(0,"Success"),
	NODBCONNECTION(-1,"No database connection"),
	HibernateException(1,"Hibernate Exception"),
	NONUNIQUEVALUE(2,"Duplicate Items"),
	REPORTNOTFOUND(-2,"Report not found"),
	NOCASHINACCOUNT(-3,"InSufficient Amount in your Cash Account"),
	NOCREDITCUSTOMER(-4,"InSufficient Credit in Customer Account"),
	NOBALANCEAMOUNT(-5,"No Balance Amount"),
	CANNOTROLLBACK(-6,"Cannot Roll Back Transaction"),
	CANNOTDELETE(-7,"Cannot Delete Record");
	
	
	
	private final int code;
	  private final String description;

	  private Status(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }

	  public int getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
}
