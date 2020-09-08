package com.java.services;

import java.util.List;
import com.java.dao.QueryAnalyzerDAO;
import com.java.dao.QueryAnalyzerImplDAO;

public class QueryAnalyzerServiceImpl implements QueryAnalyzerService{


	private QueryAnalyzerDAO query_dao = new QueryAnalyzerImplDAO();
	
	@Override
	public List<Object[]> executeQueryString(String query) {
		// TODO Auto-generated method stub
		List<Object[]> rows = query_dao.execute(query);
		
		
		return rows;
	}

	@Override
	public List<Object> executeQueryString(String table_name, String table_column_name, String column_name,String operator,
			String text) {


		List<Object> rows = query_dao.execute(table_name,table_column_name,column_name,operator,text);
		
		return rows;
	}

	@Override
	public List<Object> executeQueryString(String table_name, String table_column_name) {
	
		List<Object> rows = query_dao.execute(table_name,table_column_name);
		
		return rows;
	}
	
	@Override
	public List<Object> executeQueryString(String table_name, String table_column_name,String advance_query) {
	
		List<Object> rows = query_dao.execute(table_name,table_column_name,advance_query);
		
		return rows;
	}

}
