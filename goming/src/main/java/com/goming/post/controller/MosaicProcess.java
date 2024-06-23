package com.goming.post.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class MosaicProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String img = request.getParameter("postImg");

		String flaskUrl = "221.156.210.189:5050/test";

		try {
			URL url = new URL(flaskUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			OutputStream os = conn.getOutputStream();
			os.write(img.getBytes());
			os.flush();
			os.close();

			int responseCode = conn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream flaskResponse = conn.getInputStream();

				response.setContentType("text/html;charset=UTF-8");
			} else {
				response.getWriter().println("플라스크 연동 실패" + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Exception occured" + e.getMessage());
		}

			
		}
	private String getFileName(Part part){
		String contentDispositonHeader = part.getHeader("content-disposition");
		
		String[] elements = contentDispositonHeader.split(";");	
				
		for(String e : elements) {
			if(e.trim().startsWith("img")) 
				return e.substring(e.indexOf('=') + 1).trim().replace("\"", "");
			}
		return null;
		}
	}


