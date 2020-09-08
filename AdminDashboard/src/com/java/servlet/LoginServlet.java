package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.entity.Users;
import com.java.services.AppLoginService;
import com.java.services.AppLoginServiceImpl;
import com.java.services.UsersService;
import com.java.services.UsersServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */

	// Changed by Bijoy

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");

		String new_pass =request.getParameter("new_password");
		    
		if(new_pass != null){ //change password and login
		
			changePasswordLogin(name,new_pass,request,response);
		}
		if(pass != null){
		AppLoginService appLoginService = new AppLoginServiceImpl();

		Users user = appLoginService.checkLogin(name);		

		/*-----------------------------------------
		 * Check if User name exists in database and flag_password_admin==Y
		 * Redirect to login page with change password request
		 * If User name exists and flag_password_admin==N , check if password matches, Yes=Login No= Show error on screen
		 * On Successfull password change save new password , update_date , update_user ,flag_password_admin and redirect to dashboard
		 * On Successful Login redirect to dashboard and create session.
		 */

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		PrintWriter out= response.getWriter();
		
		if ((user != null) && !user.getUsername().equals("-1")) { //check if user exists in database
			
			if(user.getFlag_password_admin().equals("Y")){
				//change password and login
		
				request.setAttribute("user_name",user.getUsername());
				request.setAttribute("user_first_name",user.getFirst_name());
				request.setAttribute("user_last_name",user.getLast_name());
				
				request.setAttribute("user", user);
				request.setAttribute("change_password","Y");
				request.getRequestDispatcher("changepassword.jsp").forward(request, response); 

			
			}
			else{
				//match password and login
				if(!pass.equals(user.getPassword())){
					request.setAttribute("error", "Incorrect Password");
					//out.println("<font color=red>Incorrect Password</font>");
					rd.include(request, response);
				}
				else{
					
					//Login if user is an active user
					if(user.getStatus().getStatus_code().equals("ACTIVE")){
						login(user,request,response);
					}
					else{
						request.setAttribute("error", "Cannot Loggin In Active User");
						//out.println("<font color=red>Cannot Loggin In Active User</font>");
						rd.include(request, response);
						}

				}
			}
				
				
					}else{
				 request.setAttribute("error", "Invalid Credentials");
				//out.println("<font color=red>Invalid User Name</font>");
				rd.include(request, response);
			}
		}
	
	}

	
	private void changePasswordLogin(String name, String pass,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		AppLoginService appLoginService = new AppLoginServiceImpl();
		
			Users user = appLoginService.checkLogin(name);	
			user.setPassword(pass);
			user.setFlag_password_admin("N");
			if(user.getStatus().getStatus_code().equals("ACTIVE")){
				login(user,request,response);
			}
			else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				request.setAttribute("error", "Cannot Loggin In Active User");
				rd.include(request, response);
				}

	}
	private void login(Users user,HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		session.setAttribute("user_name",user.getUsername());
		session.setAttribute("user_first_name",user.getFirst_name());
		session.setAttribute("user_last_name",user.getLast_name());
		session.setAttribute("application_no",""); //To avoid setting to null
		session.setAttribute("application_name",""); //To avoid setting to null
		session.setAttribute("container_no","");
		session.setAttribute("container_name","");
		
		session.setAttribute("user", user);
		session.setAttribute("user_cod_pwd", user.getPassword());
		
		String full_path = user.getStylesheet_name();
		

		session.setAttribute("session_time_out", user.getSession_time_out());
		
		session.setAttribute("stylesheet_name",full_path);
		//Setting session time out through javascript
	//	session.setMaxInactiveInterval(30);
		Cookie userName = new Cookie("user", user.getUsername());
		Cookie userFirstName = new Cookie("firstName", user.getFirst_name());
		Cookie userLastName = new Cookie("lastName", user.getLast_name());
		//userName.setMaxAge(30*60);
		response.addCookie(userName);
		response.addCookie(userFirstName);
		response.addCookie(userLastName);
		//After successfull login change last_login_date
		user.setLast_login_date(new Date());
		user.setFlag_password_admin("N");
		UsersService service = new UsersServiceImpl();
		service.editUser(user);

	//	response.sendRedirect("home");
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
