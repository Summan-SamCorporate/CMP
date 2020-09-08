package com.java.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.entity.Status;
import com.java.entity.Users;
import com.java.services.AppLoginService;
import com.java.services.AppLoginServiceImpl;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		HttpSession sess = request.getSession(false);
		if (sess != null) {
			String curr_password = request.getParameter("curr_password");
			String new_password = request.getParameter("new_password");

			// check curr_password is same as session user
			HttpSession session = request.getSession();
			Users logged_user = (Users) session.getAttribute("user");
			if (logged_user.getPassword().equals(curr_password)) {
				// update password
				AppLoginService appLoginService = new AppLoginServiceImpl();

				logged_user.setPassword(new_password);
				logged_user.setPassword_change_date(new Date());

				Status status = appLoginService.changePassword(logged_user);
				// if password changed successfully invalidate session and ask
				// user to login again with new password
				if (status.getCode() == 0) {
					logout(request, response);
				}
			} else {
				// return error : incorrect password
				response.sendError(400, "Incorrect Password!");
			}
			
			
		} else {
			response.setStatus(HttpServletResponse.SC_GATEWAY_TIMEOUT);
			//response.sendRedirect("login.jsp");
		}
		
	}
	private void logout(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		cookie.setMaxAge(0);
    		response.addCookie(cookie);
    	}
    	}
    	//invalidate the session if exists
    	HttpSession session = request.getSession(false);
    	if(session != null){
    		session.invalidate();
    	}
    	
	}

}
