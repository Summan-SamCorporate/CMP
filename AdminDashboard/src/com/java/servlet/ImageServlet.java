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

import com.java.entity.SYSTEM_CONFIG_IMG;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.services.AccountService;
import com.java.services.AppLoginService;
import com.java.services.AppLoginServiceImpl;
import com.java.services.SystemConfigurationsService;
import com.java.services.SystemConfigurationsServiceImpl;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/ImageServlet")
@MultipartConfig
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SystemConfigurationsService service = new SystemConfigurationsServiceImpl();
       
	static Logger log = Logger.getLogger(ImageServlet.class);
	
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("id");
		SYSTEM_CONFIG_IMG image = service.get_img(); //logo image
		
/*
		ServletContext context = request.getServletContext();
		String UPLOAD_FOLDEdR = context.getRealPath("/images"); 
		
		File file = new File(UPLOAD_FOLDEdR+"/logo.jpg");
		System.out.println(file.getAbsolutePath());
		log.info(file.getAbsolutePath());
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(image.getImage_data()));*/

		if(image!=null){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 String contentType = this.getServletContext().getMimeType(image.getImage_file_name());
         
		 System.out.println("Content Type: "+ contentType);
       
         response.setHeader("Content-Type", contentType);
       
         response.setHeader("Content-Length", String.valueOf(image.getImage_data().length));
       
         response.setHeader("Content-Disposition", "inline; filename=\"" + image.getImage_file_name() + "\"");

      //   ImageIO.write(img, "JPEG", file);
 		
 		
         // Write image data to Response.
       response.getOutputStream().write(image.getImage_data());
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 

		
		Part filePart = request.getPart("image-upload"); // Retrieves <input
															// type="file"
															// name="file">
		String fileName = getFileName(filePart);

		OutputStream out = null;
		InputStream filecontent = null;

	//	ServletContext context = request.getServletContext();
	//	String UPLOAD_FOLDEdR = context.getRealPath("/images");

		File file = new File("logo.jpg");
		log.info("File created at: "+file.getAbsolutePath());
		
		try {
			filecontent = filePart.getInputStream();
			byte[] fileAsByteArray = IOUtils.toByteArray(filecontent);

			BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileAsByteArray));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "JPEG", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();

			// Save image as logo image in database
			SYSTEM_CONFIG_IMG image = service.get_img(); // logo image
			if(image==null){
				image = new SYSTEM_CONFIG_IMG();
			}
			image.setId("Logo_img");
			image.setName("Logo_img");

			image.setImage_data(imageInByte);
			image.setImage_file_name(fileName);
			service.save_img(image);

			// After Saving in database also save in images

			System.out.println(file.getAbsolutePath());
			log.info(file.getAbsolutePath());
			ImageIO.write(img, "JPEG", file);
		} catch (Exception fne) {

			log.fatal("Problems during file upload. Error: " + new Object[] { fne.getMessage() });
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}

		}

		//Return Success to server
		PrintWriter pout = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		JSONObject obj = new JSONObject();
		try {
			obj.put("success", true);

			obj.put("msg", "Success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pout.print(obj);
		pout.flush();  
	}
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    log.info( "Part Header = "+ partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
