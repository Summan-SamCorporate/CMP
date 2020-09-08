package com.java.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.entity.SYSTEM_CONFIG_IMG;
import com.java.entity.Status;
import com.java.entity.System_Config_Tiles;
import com.java.entity.Type;
import com.java.entity.Users;
import com.java.services.AccountService;
import com.java.services.AppLoginService;
import com.java.services.AppLoginServiceImpl;
import com.java.services.SystemConfigurationsService;
import com.java.services.SystemConfigurationsServiceImpl;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/TilesManagementServlet")
@MultipartConfig
public class TilesManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SystemConfigurationsService service = new SystemConfigurationsServiceImpl();
       
	static Logger log = Logger.getLogger(TilesManagementServlet.class);
	ObjectMapper objectMapper = new ObjectMapper();
	
    public TilesManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		ArrayList<System_Config_Tiles> tiles =(ArrayList<System_Config_Tiles>) service.getAllTiles();		
		
		if(tiles !=null){ 
	        response.setContentType("application/json");            
	        response.setCharacterEncoding("UTF-8");
	       
	      
	        objectMapper.writeValue(response.getOutputStream(), tiles);
		 		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			//Get logged in user from session
			String logged_user =(String) request.getSession(false).getAttribute("user_name");
			
			String id = request.getParameter("id");
			String display_name = request.getParameter("display_name");
			String icon = request.getParameter("icon");
			String background_color = request.getParameter("background_color");
			String url = request.getParameter("url");
			
		
		System_Config_Tiles new_tile = new System_Config_Tiles();
		new_tile.setId(id);
		new_tile.setDisplay_name(display_name);
		//new_tile.setIcon(icon);
		new_tile.setBackground_color(background_color);
		new_tile.setUrl(url);
		
		
		
		if(id.equals("")){
		new_tile.setId(display_name);
		new_tile.setCreated_user(logged_user);
		new_tile.setCreated_date(new Date());
		service.addTile(new_tile);
		}
		else{
		
			service.updateTile(new_tile);
		}
		 	}
		else {
			response.sendRedirect("login.jsp");
		}
	}
}
