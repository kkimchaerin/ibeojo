package com.goming.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.post.model.PostDAO;
import com.goming.post.model.PostDTO;
import com.google.gson.Gson;

public class PostLoaderService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String gender = request.getParameter("gender");
        String style = request.getParameter("style");
        String season = request.getParameter("season");
        
        PostDTO filter = new PostDTO();
        filter.setUserGender(gender);
        filter.setStyle(style);
        filter.setSeason(season);
        
        PostDAO postDAO = new PostDAO();
        List<String> posts = postDAO.getPostsByFilters(filter);
        
        // Gson 객체 생성
        Gson gson = new Gson();
        
        // List<PostDTO>를 JSON 형식의 문자열로 변환
        String json = gson.toJson(posts);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

}
