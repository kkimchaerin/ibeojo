package com.goming.mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.mypage.model.MyPageDAO;
import com.goming.mypage.model.MyPageDTO;

public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("user_email");
		String nickname = request.getParameter("user_nick");
		String password = request.getParameter("user_pw");

		MyPageDTO dto = new MyPageDTO();
		MyPageDAO dao = new MyPageDAO();

		dto.setUser_email(email);
		dto.setUser_nick(nickname);
		dto.setUser_pw(password);

		int cnt = dao.deleteUser(dto);

		if (cnt > 0) {
			System.out.println("회원 탈퇴 성공");
			response.sendRedirect("First.jsp");
			HttpSession session = request.getSession();
			session.invalidate();
		} else {
			System.out.println("회원 탈퇴 실패");
			response.sendRedirect("UserDelete.jsp");
		}

	}

}
