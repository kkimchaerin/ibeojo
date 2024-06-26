package com.goming.like.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;

public class LikeSelectController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("lat");
		
		LikeDAO dao = new LikeDAO();
		List<LikeDTO> dtolist = dao.weatherSelectAll(email);
		
		
	}

}
