package com.goming.post.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.post.model.PostDAO;
import com.goming.post.model.PostDTO;


public class MosaicProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		PostDAO dao = new PostDAO();
		
		int idx = Integer.parseInt(dao.getidx());
		String img = dao.getimg();
		
		response.sendRedirect("http://192.168.219.47:5050/mosaic?idx="+idx+"&img="+img);
		
	}

}
