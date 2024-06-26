package com.goming.post.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.post.model.PostDAO;

public class UpdateService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String img = request.getParameter("img");
		
		PostDAO dao = new PostDAO();
		
		int cnt = dao.update(idx, img);
		
		
		if (cnt > 0) {
			System.out.println("업로드 완료");
			response.sendRedirect("Main.jsp?upload=success");
				System.out.println("모자이크");
		} else {
			System.out.println("업로드 실패");
			response.sendRedirect("Main.jsp?upload=failure");
		}
		
	}

}
