package com.goming.like.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;

public class CheckLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * request.setCharacterEncoding("UTF-8");
		 * 
		 * ObjectMapper mapper = new ObjectMapper();
		 * 
		 * Map<String, String> requestData = mapper.readValue(request.getReader(),
		 * Map.class);
		 * 
		 * String img = requestData.get("param");
		 * 
		 * // 좋아요 여부를 체크
		 * 
		 * // 세션에서 회원의 이메일 받아오기 HttpSession session = request.getSession(); String
		 * user_email = (String)session.getAttribute("user_email");
		 * 
		 * // 해당 게시글의 idx 받아오기 LikeDAO dao = new LikeDAO(); LikeDTO dto = new LikeDTO();
		 * 
		 * dto = dao.check_like(img, user_email);
		 * 
		 * Map<String, Integer> resultData = new HashMap<String, Integer>();
		 * 
		 * int cnt = 0; if(dto != null) { cnt = 1; }else { cnt = 0; }
		 * 
		 * resultData.put("reponseParam", cnt);
		 * 
		 * response.setContentType("application/json");
		 * response.setCharacterEncoding("UTF-8");
		 * 
		 * mapper.writeValue(response.getWriter(), resultData);
		 */
		
	}

}
