package com.goming.like.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;
import com.goming.ml.model.ml_DTO;

public class LikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		LikeDTO dto = new LikeDTO();
//		LikeDAO dao = new LikeDAO();
		
	}

}
