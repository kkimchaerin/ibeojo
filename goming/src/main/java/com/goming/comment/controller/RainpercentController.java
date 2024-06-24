package com.goming.comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RainpercentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		int rain = 30;
		
		if(rain >= 20) {
			String rainper = "비가 올 것 같아요 우산을 챙겨주세요!";
			session.setAttribute("rainper", rainper);
		}
		
		
		
		
		response.sendRedirect("Main.jsp");
		
	}

}
