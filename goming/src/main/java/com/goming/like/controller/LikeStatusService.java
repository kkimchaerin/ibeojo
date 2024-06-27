package com.goming.like.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.like.model.LikeDAO;
import com.goming.like.model.LikeDTO;

import java.io.IOException;
import java.io.PrintWriter;

public class LikeStatusService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        String user_email = (String) session.getAttribute("user_email");
        int post_idx = Integer.parseInt(request.getParameter("post_idx"));
        
        // 세션에 이메일이 없을 경우 로그인 페이지로 리디렉션
        if (user_email == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        LikeDTO like = new LikeDTO(post_idx, user_email);
        LikeDAO dao = new LikeDAO();
        
        boolean isLiked = dao.isLiked(like);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"isLiked\": " + isLiked + "}");
    }
}
