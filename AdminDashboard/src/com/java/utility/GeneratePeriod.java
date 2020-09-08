package com.java.utility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.services.PeriodService;
import com.java.services.PeriodServiceImpl;

/**
 * Servlet implementation class GeneratePeriod
 */
@WebServlet("/GeneratePeriod")
public class GeneratePeriod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	

	PeriodService period_service = new PeriodServiceImpl();
	
    public GeneratePeriod() {
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
	 * Servlet created to generate period according to the utility selected in Period Management
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String duration = request.getParameter("duration");
		
		if((duration!=null) ){
			if(duration.equals("months")){
			String months = request.getParameter("months");
			
			if(months != null){
				if(months.equals("jan_dec")){
					
				}else if(months.equals("apr_mar")){
					
				}
			}
			}
		}
		
	}
	
	private void runJan_Dec(HttpServletRequest request,HttpServletResponse response){
		
		HttpSession session = request.getSession(false);
		String application_no = (String) session.getAttribute("application_no");

		//Get periods from Jan - Dec 
		
		period_service.getAll(application_no);
		
	}
	private void runApr_Mar(HttpServletRequest request,HttpServletResponse response){

		HttpSession session = request.getSession(false);
		String application_no = (String) session.getAttribute("application_no");

		//Get periods from Jan - Dec 
		
		period_service.getAll(application_no);
	}

}
