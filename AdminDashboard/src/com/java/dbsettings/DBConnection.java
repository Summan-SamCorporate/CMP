package com.java.dbsettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	// Establishing DataBase connection.
	
	private static String url = "";
	private static String uname= "";
	private static String pass = "";
	private static String driver = "";
	private static Connection con= null;
	
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		DBConnection.url = url;
	}
	public static String getUname() {
		return uname;
	}
	public static void setUname(String uname) {
		DBConnection.uname = uname;
	}
	public static String getPass() {
		return pass;
	}
	public static void setPass(String pass) {
		DBConnection.pass = pass;
	}
	public static String getDriver() {
		return driver;
	}
	public static void setDriver(String driver) {
		DBConnection.driver = driver;
	}
	public static Connection getCon() {
		return con;
	}
	public static void setCon(Connection con) {
		DBConnection.con = con;
	};
	
	public static boolean connectDb() throws SQLException{
		
		System.out.println("inside connectDb()");
		
		try{
			Class.forName(DBConstants.driver);
			System.out.println("DBConstants.driver : " + DBConstants.driver);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error in loading driver " + driver + " : " + e);
			return false;
		}
		try {
			System.out.println("DBConstants.url : " + DBConstants.url);
			System.out.println("DBConstants.uname : " + DBConstants.uname);
			System.out.println("DBConstants.pass : " + DBConstants.pass);
			DBConstants.conn = DriverManager.getConnection(DBConstants.url, DBConstants.uname, DBConstants.pass);
		} catch (Exception e) {
			System.out.println("Error in establishing connection to " + url + " : " + e);
			return false;
		}
		return true;
	}
	
	public static boolean closeConnection(){
		try{
			if (null != con) {

				con.close();
			}
			con = null;
			
		}catch(SQLException e){
			System.err.println("Error in closing connection : " + e);
			e.printStackTrace();
			return false;	
		}
		return true;
	}
}
