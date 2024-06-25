package com.goming.post.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.post.model.PostDAO;
import com.goming.post.model.PostDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String saveDir = "images"; // 저장되는 폴더
		String encType = "UTF-8";
		int maxSize = 5 * 1024 * 1024; // 최대 업로드 크기 5MB / byte - KB - MB

		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveDir);
		System.out.println("실제 저장경로: " + realFolder);

		// 파일 업로드 객체 생성
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

		String userEmail = "crong@gmail.com";
//		String userEmail = multi.getParameter("userEmail");
		String postImg = multi.getFilesystemName("postImg");
		String userGender = multi.getParameter("userGender");
		String style = multi.getParameter("style");
		String season = multi.getParameter("season");
		String faceCheck = multi.getParameter("faceCheck");

		System.out.println("userGender: " + userGender);
		System.out.println("style: " + style);
		System.out.println("season: " + season);

		// String userEmail, String postImg, String userGender, String style, String
		// season
		

		PostDTO post = new PostDTO(userEmail, postImg, userGender, style, season);
		PostDAO dao = new PostDAO();
		int cnt = dao.postInsert(post);

		Writer out = response.getWriter();
		
		
		System.out.println(faceCheck);

		if (cnt > 0) {
			out.write("<script>alert('업로드되었습니다 :)');</script>");
			System.out.println("업로드 완료");
			// response.sendRedirect("BoardSelectAllService"); // 데이터 전달 없이 이동만 하기 때문에
			// redirect 사용
			// 전달할 데이터가 있다면 forward 사용
			if(faceCheck.equals("ON")) {
				response.sendRedirect("MosaicProcess");
			}else {
				response.sendRedirect("Main.jsp");
			}
		} else {
			System.out.println("업로드 실패");
			response.sendRedirect("Main.jsp");
		}
	}

	
}
