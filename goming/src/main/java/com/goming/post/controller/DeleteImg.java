package com.goming.post.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goming.post.model.PostDAO;

public class DeleteImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> requestData = mapper.readValue(request.getReader(), Map.class);
		
		String img = requestData.get("param");

		System.out.println("deleteimg"+img);
		
		PostDAO dao = new PostDAO();
		int deleteResult = dao.deletePost(img);
		
		Map<String, Object> responseMap = new HashMap<>();
		if (deleteResult > 0) {
			response.setStatus(HttpServletResponse.SC_OK); // 200 상태 코드
			responseMap.put("message", "게시물이 성공적으로 삭제되었습니다.");
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 상태 코드
			responseMap.put("message", "게시물 삭제에 실패했습니다.");
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		mapper.writeValue(response.getWriter(), responseMap);
	}

}
