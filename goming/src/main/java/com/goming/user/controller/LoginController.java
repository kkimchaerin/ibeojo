package com.goming.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.maven.model.Member;
import com.smhrd.maven.model.MemberDAO;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2. 데이터 가지고 오기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");

		// 3. Member 객체 생성
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		
		// 4. DAO 객체 생성 -> 메소드 실행
		MemberDAO dao = new MemberDAO();
		Member m = dao.login(member);

		if(m != null) {
			// 로그인 성공
			System.out.println("success");
			
			// 이동
			response.sendRedirect("index.jsp");
		}
		else {
			// 로그인 실패
			System.out.println("fail");
		}
	}

}
