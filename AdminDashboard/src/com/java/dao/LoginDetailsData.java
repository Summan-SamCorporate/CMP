package com.java.dao;

import com.java.bean.USERS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.java.dbsettings.*;


public class LoginDetailsData {
	public USERS getUserDetails(String userName, String password) {
		
		
		//new code//
		//changed for log4j
        Logger infoMessagesLogger = Logger.getLogger("LoginDetailsData");
  
        infoMessagesLogger.info("hello admin");
        infoMessagesLogger.warn("warn message");
        infoMessagesLogger.error("error message");
        infoMessagesLogger.debug("debug message");   
		
		
		System.out.println("inside getUserDetails()");

		USERS user = new USERS();
		boolean connect = false;
		try {
			connect = DBConnection.connectDb();
		} catch (Exception e) {
			System.out.println("Error in establishing connection with DB " + e);
			e.printStackTrace();
		}

		Statement stmt = null;
		ResultSet rs = null;
		System.out.println(userName+" : " + password);
		String selectQuery = "select username, password from USERS where username = '"
				+ userName + "' and password = '" + password +  "' and status = 'admin'";
		System.out.println(selectQuery);
                System.out.println(DBConstants.conn);

		try {
			stmt = DBConstants.conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
			}
			System.out.println("Successfully fetching all impact program Names");

		} catch (Exception e) {
			System.out.println("Error in fetching data from userdetails in LoginDetailsData.getUserDetails()");
			e.printStackTrace();
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != stmt)
					stmt.close();
				stmt = null;
				rs = null;
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return user;
	}
}
