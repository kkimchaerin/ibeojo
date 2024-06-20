package com.goming.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.mypage.model.MyPageDAO;
import com.goming.mypage.model.MyPageDTO;



public class ImgController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();

		MyPageDTO dto = (MyPageDTO)session.getAttribute("userinfo");
		MyPageDAO dao = new MyPageDAO();
		
		String email = dto.getUser_email();
		
		MyPageDTO dto_img = new MyPageDTO();
		
		dto_img.setUser_email(email);
		
		List<MyPageDTO> img_list = dao.SelectMyPageImg(dto_img);
		
		session.setAttribute("img_list", img_list);
		
		response.sendRedirect("MyPage.jsp");
	}

}
