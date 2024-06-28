package com.goming.like.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.goming.like.model.LikeDAO;

public class LikeToggleController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");

		System.out.println("LikeToggleController : " );
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line);
		}
		JSONObject jsonRequest = new JSONObject(sb.toString());

		// JSON 객체에서 post_img 값 추출
		String post_img = jsonRequest.getString("post_img");
		String user_email = jsonRequest.getString("user_email");

		post_img = post_img.replace("./post/", "");
		System.out.println("LikeToggleController : " + post_img);

		// DAO를 사용하여 좋아요 갯수 가져오기
		LikeDAO dao = new LikeDAO();
		dao.likeToggle(post_img, user_email);
		System.out.println("dao.likeToggle(post_img, user_email); : " + post_img + user_email);
		int likebool = dao.likeToggleCount(post_img, user_email);
		System.out.println("likebool : " + likebool);
		
		int likeCount = dao.LikeSelectAllCount(post_img);
		System.out.println("LikeToggleController : ,likeCount" + likeCount);

		// 결과를 JSON 객체로 변환하여 응답
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("likebool", likebool);
		jsonResponse.put("likeCount", likeCount);
		System.out.println("LikeToggleController : " + jsonResponse.toString());

		// 응답 보내기
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse.toString());

	}

}
