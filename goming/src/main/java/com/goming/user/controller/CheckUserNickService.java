package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;

public class CheckUserNickService extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		String user_nick = request.getParameter("user_nick");
		System.out.println("CheckUserIdService : " + user_nick);
		
		user_DAO dao = new user_DAO();
		
		int user = dao.selectnick(user_nick);
		
		
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(String.valueOf(user));
		
		
	}

}
