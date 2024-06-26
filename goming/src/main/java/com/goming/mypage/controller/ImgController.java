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
import com.goming.post.model.PostDTO;

public class ImgController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// userinfo session의 데이터(회원정보) 가져오기
		MyPageDTO dto = (MyPageDTO) session.getAttribute("userinfo");
		MyPageDAO dao = new MyPageDAO();

		// 게시글 가져오기 위해 회원 email 받기
		String email = dto.getUser_email();

		MyPageDTO dto_img = new MyPageDTO();
		dto_img.setUser_email(email);

		List<PostDTO> img_list = dao.SelectMyPageImg2(dto_img);

		if (img_list != null) {
			// 불러오기 성공하면 세션에 저장 후 마이페이지로 이동
			System.out.println("성공한거");
			session.setAttribute("img_list", img_list);
//			System.out.println(img_list.size());
//			System.out.println(img_list.get(0));
//			System.out.println(img_list.get(0).getSeason());
//			System.out.println(img_list.get(0).getStyle_tag());
//			System.out.println(img_list.get(0).getPost_img());
			response.sendRedirect("MyPage.jsp");
		} else {
			// 불러오기 실패하면 이전 페이지로 이동
			response.sendRedirect("Error.jsp");
		}
	}
}
