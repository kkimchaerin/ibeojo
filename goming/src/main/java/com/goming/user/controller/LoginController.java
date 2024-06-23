package com.goming.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;
import com.goming.user.model.user_DTO;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 한글 인코딩
		request.setCharacterEncoding("UTF-8");

		// 2. 데이터 가지고 오기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");

		// 3. dto 객체 생성
		user_DTO dto = new user_DTO();
		dto.setUser_email(id);
		dto.setUser_pw(pw);

		// 4. DAO 객체 생성 -> 메소드 실행
		user_DAO dao = new user_DAO();
		user_DTO m = dao.login(dto);

		if (m != null) {
			// 로그인 성공
			System.out.println("success");

			// 이동
			response.sendRedirect("CommentController");
		} else {
			// 로그인 실패
			System.out.println("LoginController : fail");
			request.setAttribute("errorMessage", "아이디와 비밀번호를 확인하세요.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
