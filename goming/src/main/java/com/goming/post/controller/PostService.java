package com.goming.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goming.post.model.PostDAO;
import com.goming.post.model.PostDTO;
import com.google.gson.Gson;

public class PostService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String gender = request.getParameter("gender");
	        String style = request.getParameter("style");
	        String season = request.getParameter("season");

	        PostDAO dao = new PostDAO();
	        List<PostDTO> images = dao.getImagesByFilters(gender, style, season);

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        Gson gson = new Gson();
	        String json = gson.toJson(images);

	        PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
	    }

}
