package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;
import com.goming.user.model.user_DTO;

public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("JoinController : " + "join");
//		1. 한글 인코딩
		request.setCharacterEncoding("UTF-8");

//		2. 데이터 가지고 오기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String nick = request.getParameter("user_nick");
		String gender = request.getParameter("user_gender");
		String pre = request.getParameter("user_preference");

		System.out.println("JoinController : " + id);
		System.out.println("JoinController : " + pw);
		System.out.println("JoinController : " + nick);
		System.out.println("JoinController : " + gender);
		System.out.println("JoinController : " + pre);

		user_DTO dto = new user_DTO(id, pw, nick, gender, pre);

//		3. 데이터베이스 기능 호출
//		dao : memebermapper 연결
		user_DAO dao = new user_DAO();
		int row = dao.join(dto);

//		로그인, 회원가입 성공하면 dao의 row 값 받기
//		row라고 변수명 동일하게 설정
		if (row > 0) {
			System.out.println("JoinController : " + "success");
			request.setAttribute("success", "true");
		} else {
			if (row == -1) {
				System.out.println("JoinController : " + "아이디 중복");
			} else {
				System.out.println("JoinController : " + "닉네임 중복");
			}
			System.out.println("JoinController : " + "fail");
			request.setAttribute("success", "false");
		}

		request.getRequestDispatcher("First.jsp").forward(request, response);

	}

}
