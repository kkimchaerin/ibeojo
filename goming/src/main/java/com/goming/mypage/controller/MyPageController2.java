package com.goming.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.mypage.model.MyPageDAO;
import com.goming.mypage.model.MyPageDTO;


public class MyPageController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 임시 회원 정보(세션으로 받아와야 함)
		String email = "asd";
		String pw = "1234";
		
		MyPageDAO dao = new MyPageDAO();
		MyPageDTO dto = new MyPageDTO();
		
		// dto 에 email, pw만 담아서 전달
		dto.setUser_email(email);
		dto.setUser_pw(pw);
		
		dto = dao.SelectMyPageInfo(dto);
		
		HttpSession session = request.getSession();
		
		if(dto != null) {
			session.setAttribute("userinfo", dto);
			response.sendRedirect("ImgController");
		}else {
			System.out.println("회원 정보 가져오기 실패");
		}
		
	}

}
