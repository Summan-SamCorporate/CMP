package com.java.services;

import java.util.List;

import com.java.dao.QueryAnalyzerDAO;
import com.java.dao.QueryAnalyzerImplDAO;

public interface QueryAnalyzerService {

	
	public List<Object[]> executeQueryString(String query);
	
	public List<Object> executeQueryString(String table_name, String table_column_name);

	public List<Object> executeQueryString(String table_name, String table_column_name,String advance_query);

	public List<Object> executeQueryString(String table_name, String table_column_name, String column_name,String operator,
			String text);
}
