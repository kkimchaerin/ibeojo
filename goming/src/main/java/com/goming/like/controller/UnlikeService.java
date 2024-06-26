package com.goming.like.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;

public class UnlikeService extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       request.setCharacterEncoding("UTF-8");

	        HttpSession session = request.getSession();

	        int post_idx = Integer.parseInt(request.getParameter("post_idx"));
	        String user_email = (String) session.getAttribute("user_email");
	        
	        // 세션에 이메일과 비밀번호가 없을 경우 로그인 페이지로 리디렉션
	        if(user_email == null) {
	            response.sendRedirect("Login.jsp");
	            return;
	        }

	        LikeDTO like = new LikeDTO(post_idx, user_email);
	        LikeDAO dao = new LikeDAO();

	        int cnt = dao.likeDelete(like); // 좋아요 정보 삭제 메서드 호출

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        JSONObject jsonResponse = new JSONObject();
	        
	        if(cnt > 0) {
	            jsonResponse.put("result", "success");
	            System.out.println("좋아요 취소 완료");
	        } else {
	            jsonResponse.put("result", "failure");
	            System.out.println("좋아요 취소 실패");
	        }
	        
	        response.getWriter().write(jsonResponse.toString());
	}

}
