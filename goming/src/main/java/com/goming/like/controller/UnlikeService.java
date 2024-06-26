package com.goming.like.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;

public class UnlikeService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       request.setCharacterEncoding("UTF-8");

	        HttpSession session = request.getSession();

	        int post_idx = Integer.parseInt(request.getParameter("post_idx"));
	        String user_email = (String) session.getAttribute("user_email");

	        LikeDTO like = new LikeDTO(post_idx, user_email);
	        LikeDAO dao = new LikeDAO();

	        int cnt = dao.likeDelete(like); // 좋아요 정보 삭제 메서드 호출

	        if (cnt > 0) {
	            System.out.println("좋아요 취소 완료");
	            response.sendRedirect("Main.jsp");

	        } else {
	            System.out.println("좋아요 취소 실패");
	            response.sendRedirect("Main.jsp");
	        }
	}

}
