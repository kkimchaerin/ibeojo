package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.user.model.user_DAO;

public class FindPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 한글 인코딩 설정
		request.setCharacterEncoding("UTF-8");

		// 2. 입력된 이메일 주소 받기
		String userEmail = request.getParameter("user_email");
		String newPassword = request.getParameter("user_pw");

		// 3. DAO 객체 생성 및 비밀번호 수정 메소드 호출
		user_DAO dao = new user_DAO();
		boolean isUpdated = dao.updatePasswordByEmail(userEmail, newPassword);
		System.out.println("boolean isUpdated = dao.updatePasswordByEmail(userEmail, newPassword);");
		System.out.println("FindPwController : " + isUpdated);
		// 4. 결과에 따른 처리
		if (isUpdated) {
			// 비밀번호 변경 성공
			request.setAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
		} else {
			// 비밀번호 변경 실패
			request.setAttribute("message", "해당 이메일로 등록된 사용자가 없습니다.");
		}

		request.getRequestDispatcher("FindPw.jsp").forward(request, response);
	}

}
