package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;

public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 한글 인코딩 설정
		request.setCharacterEncoding("UTF-8");

		// 2. 입력된 이메일 주소 받기
		String userNick = request.getParameter("user_nick");

		// 3. DAO 객체 생성 및 아이디 찾기 메소드 호출
		user_DAO dao = new user_DAO(); // UserDAO는 데이터베이스 접근을 위한 DAO 클래스입니다. 클래스명은 실제로 사용하는 DAO 클래스명에 맞게 수정이 필요합니다.
		String foundId = dao.findIdByNick(userNick);

		// 4. 결과에 따른 처리
		if (foundId != null) {
			// 아이디를 찾은 경우
			request.setAttribute("foundId", foundId);
		} else {
			// 아이디를 찾지 못한 경우
			request.setAttribute("message", "해당 이메일로 등록된 아이디가 없습니다.");
		}

		request.getRequestDispatcher("FindId.jsp").forward(request, response);

	}
}