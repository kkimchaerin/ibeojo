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

		// 로그인에서 세션 받아오기
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("user_email");
		String pw = (String) session.getAttribute("user_pw");

		// 세션에 이메일과 비밀번호가 없을 경우 로그인 페이지로 리디렉션
		if (email == null || pw == null) {
			response.sendRedirect("Login.jsp");
			return;
		}

		MyPageDAO dao = new MyPageDAO();
		MyPageDTO dto = new MyPageDTO();

		// dto 에 email, pw만 담아서 전달
		dto.setUser_email(email);
		dto.setUser_pw(pw);

		dto = dao.SelectMyPageInfo(dto);

		if (dto != null) {
			session.setAttribute("userinfo", dto);
			// 이미지 불러오는 con으로 연결
			response.sendRedirect("ImgController");
		} else {
			System.out.println("회원 정보 가져오기 실패");
			response.sendRedirect("Error.jsp");
		}

	}

}
