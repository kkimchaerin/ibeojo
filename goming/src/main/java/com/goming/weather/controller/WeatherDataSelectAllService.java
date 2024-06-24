package com.goming.weather.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goming.weather.model.LocalDateAdapter;
import com.goming.weather.model.WeatherDAO;
import com.goming.weather.model.WeatherDTO;

public class WeatherDataSelectAllService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("selectAllWeatherInfo : weatherlist : ");
        WeatherDAO dao = new WeatherDAO();

        // DAO를 통해 데이터베이스에서 데이터를 가져옴
        List<WeatherDTO> weatherlist = dao.weatherSelectAll();
        System.out.println("selectAllWeatherInfo : weatherlist : " + weatherlist.size());
        System.out.println("selectAllWeatherInfo : weatherlist : " + weatherlist.size());
        System.out.println("selectAllWeatherInfo : weatherlist : " + weatherlist.size());
        System.out.println("selectAllWeatherInfo : weatherlist : " + weatherlist.size());
        System.out.println("selectAllWeatherInfo : weatherlist : " + weatherlist.size());
        System.out.println("selectAllWeatherInfo : weatherlist : " + weatherlist.size());

        // Gson을 사용하여 Java 객체를 JSON 문자열로 변환
        Gson gson = new GsonBuilder()
        	    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        	    .create();

        String json = gson.toJson(weatherlist);

        
        
        // 클라이언트로 JSON 데이터를 응답으로 전송
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
