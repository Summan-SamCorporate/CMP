package com.java.entity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.hibernate.SessionFactoryObserver;
*/import org.hibernate.cfg.AnnotationConfiguration;


public class HibernateUtil {
	private static SessionFactory sessionFactory;

public static SessionFactory getSessionFactory() {
	AnnotationConfiguration config = new AnnotationConfiguration();
	config.configure("/com/java/entity/hibernate.cfg.xml");
	sessionFactory = config.buildSessionFactory();
return sessionFactory;
}

	public static Session openSession() {
		
		if(sessionFactory == null)
			getSessionFactory();
		
		return sessionFactory.openSession();
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("JSESSIONID")){
    			System.out.println("JSESSIONID="+cookie.getValue());
    		}
    		cookie.setMaxAge(0);
    		response.addCookie(cookie);
    	}
    	}
    	//invalidate the session if exists
    	HttpSession session = request.getSession(false);
    	if(session != null){
    		session.invalidate();
    	}
		response.sendRedirect("login.jsp");
	}

	
}