package com.goming.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.mypage.model.MyPageDAO;
import com.goming.mypage.model.MyPageDTO;

public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = (String)request.getAttribute("email");
		String nickname = (String)request.getAttribute("nickname");
		String password = (String)request.getAttribute("password");
		
		MyPageDTO dto = new MyPageDTO();
		MyPageDAO dao = new MyPageDAO();
		
		dto.setUser_email(email);
		dto.setUser_nick(nickname);
		dto.setUser_pw(password);
		
		int cnt = dao.deleteUser(dto);
		
		if(cnt > 0) {
			System.out.println("회원 탈퇴 성공");
			response.sendRedirect("Fisrt_Page.jsp");
		}else {
			System.out.println("회원 탈퇴 실패");
			response.sendRedirect("UserDelete.jsp");
		}
		
	}

}

