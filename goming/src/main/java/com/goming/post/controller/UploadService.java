package com.goming.post.controller;

import java.io.IOException;
import java.io.Writer;
import java.security.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.post.model.PostDAO;
import com.goming.post.model.PostDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String saveDir = "post"; // 저장되는 폴더
		String encType = "UTF-8";
		int maxSize = 5 * 1024 * 1024; // 최대 업로드 크기 5MB / byte - KB - MB

		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveDir);
		System.out.println("실제 저장경로: " + realFolder);
		// 실제 저장경로:
		// C:\Users\smhrd\git\shin\ibeojo\goming\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\goming\post

		// 파일 업로드 객체 생성
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

		String userEmail = (String) session.getAttribute("user_email");
		String userNick = (String) session.getAttribute("user_nick");
		String postImg = multi.getFilesystemName("postImg");
		char gender = multi.getParameter("gender").charAt(0);
		String style = multi.getParameter("style");
		String season = multi.getParameter("season");
		String comment = multi.getParameter("comment");
		String faceCheck = multi.getParameter("faceCheck");
		
		
//        System.out.println("userGender: " + gender);
//        System.out.println("style: " + style);
//        System.out.println("season: " + season);
//        System.out.println("comment: " + comment);
        
        //String user_email, String post_img, char gender, String style_tag, String season, String comment
		PostDTO post = new PostDTO(userEmail, userNick, postImg, gender, style, season, comment); 
		PostDAO dao = new PostDAO();
		int cnt = dao.postInsert(post);

		Writer out = response.getWriter();

		if (cnt > 0) {
			System.out.println("업로드 완료");
			if ("ON".equals(faceCheck)) {
				response.sendRedirect("MosaicProcess");
				System.out.println("모자이크");
			} else {
				response.sendRedirect("Main.jsp?upload=success");
			}
		} else {
			System.out.println("업로드 실패");
			response.sendRedirect("Main.jsp?upload=failure");
		}
	}

}
