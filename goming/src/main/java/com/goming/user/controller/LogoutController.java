package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false); // 현재 세션이 있으면 가져오고, 없으면 null 반환
		if (session != null) {
			session.invalidate(); // 세션 무효화 (로그아웃)
			System.out.println("로그아웃!!");
		}
		response.sendRedirect("First.jsp"); // 로그아웃 후 첫 페이지로 이동
		System.out.println("로그아웃");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // POST 요청도 로그아웃 처리와 동일하게 처리
	}

}
