package com.goming.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.post.model.PostDAO;
import com.goming.post.model.PostDTO;
import com.google.gson.Gson;

@WebServlet("/PostLoaderService")
public class PostLoaderService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청에서 필터링 정보를 가져옴
		char gender = request.getParameter("gender").charAt(0);
		String style_tag = request.getParameter("style_tag");
		String season = request.getParameter("season");
		String sortBy = request.getParameter("sortBy");
		
		// 필터링 정보를 담은 PostDTO 객체 생성
		PostDTO filter = new PostDTO();
		filter.setGender(gender);
		filter.setStyle_tag(style_tag);
		filter.setSeason(season);
		filter.setSortBy(sortBy);

		// DAO를 통해 필터링된 게시물 리스트 가져오기
		PostDAO dao = new PostDAO();
		List<PostDTO> filteredPosts = dao.getPostsByFilters(filter);

		// JSON 형식으로 응답
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String json = gson.toJson(filteredPosts);
		response.getWriter().write(json);
	}

}
