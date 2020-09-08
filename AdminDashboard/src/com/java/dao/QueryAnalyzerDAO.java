package com.java.dao;

import java.util.List;

public interface QueryAnalyzerDAO {

	public List<Object[]> execute(String query);
	
	public List<Object> execute (String table_name, String table_column_name, String column_name,String operator,String text);
	public List<Object> execute (String table_name, String table_column_name);
	public List<Object> execute (String table_name, String table_column_name,String advance_query);
}
