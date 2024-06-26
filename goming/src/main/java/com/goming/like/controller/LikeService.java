package com.goming.like.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;

public class LikeService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		int post_idx = Integer.parseInt(request.getParameter("post_idx"));
		
		System.out.println("post_idx!!"+ post_idx);
		String user_email = (String)session.getAttribute("user_email");
		
		// 세션에 이메일과 비밀번호가 없을 경우 로그인 페이지로 리디렉션
		if(user_email == null) {
			response.sendRedirect("Login.jsp");
		}
		
		LikeDTO like = new LikeDTO(post_idx, user_email);
		LikeDAO dao = new LikeDAO();
		
		int cnt = dao.likeInsert(like);
		
		if(cnt > 0) {
			//out.write("<script>alert('업로드되었습니다 :)');</script>");
			System.out.println("좋아요 완료");
			response.sendRedirect("Main.jsp");

		}else {
			System.out.println("좋아요 실패");
			response.sendRedirect("Main.jsp");
		}
	}

}
