package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.entity.Users;
import com.java.services.UsersService;
import com.java.services.UsersServiceImpl;

/**
 * Servlet implementation class GeneralConfigurationServlet
 */
@WebServlet("/GeneralConfigurationServlet")
public class GeneralConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Users logged_user = null;
	UsersService user_service = new UsersServiceImpl();
	
    public GeneralConfigurationServlet() {
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
		
		String session_time_out = request.getParameter("session_time_out");
		//Get current logged user and update value in Users table
		HttpSession session = request.getSession(false);
		if (session != null) {
			logged_user = (Users) session.getAttribute("user");
			if(session_time_out != null){
			logged_user.setSession_time_out(Integer.parseInt(session_time_out));
			user_service.editUser(logged_user);
			}
			
		}
		
		//Also update session 
		request.setAttribute("session_time_out",logged_user.getSession_time_out());
	}

}
