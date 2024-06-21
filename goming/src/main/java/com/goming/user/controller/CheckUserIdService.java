package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;
import com.goming.user.model.user_DTO;

public class CheckUserIdService extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
//		user_id
		String user_id = request.getParameter("user_id");
		System.out.println("CheckUserIdService : " + user_id);
		
		user_DAO dao = new user_DAO();
		
		int user = dao.selectemail(user_id);
		
		
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(String.valueOf(user));
		
	}

}
