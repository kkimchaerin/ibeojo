package com.goming.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;
import com.goming.user.model.user_DTO;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		user_DAO dao = new user_DAO();

		List<user_DTO> members = dao.selectAll();
		
		request.setAttribute("selectMembers", members);
		
		System.out.println("레츠고 admin");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin.jsp");
		
		dispatcher.forward(request, response);

		
	}

}
