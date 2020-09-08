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
@WebServlet("/ChangeThemeServlet")
public class ChangeThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeThemeServlet() {
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
			String theme_code = request.getParameter("theme_code");
			HttpSession session = request.getSession();
			Users logged_user = (Users) session.getAttribute("user");

			logged_user.setStylesheet_name(theme_code);

			AppLoginService appLoginService = new AppLoginServiceImpl();

			Status status = appLoginService.changeTheme(logged_user);
			if (status.getCode() == 0) {
				String full_path = theme_code;

				session.setAttribute("stylesheet_name", full_path);
			} else {
				response.sendError(400, "Incorrect Password!");
			}
		} else {
			response.sendRedirect("login.jsp");
		}
			}
	

}
