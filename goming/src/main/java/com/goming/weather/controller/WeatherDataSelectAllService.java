package com.goming.weather.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goming.weather.model.CoordinateConverter;
import com.goming.weather.model.CoordinateDTO;
import com.goming.weather.model.LocalDateAdapter;
import com.goming.weather.model.WeatherDAO;
import com.goming.weather.model.WeatherDTO;

public class WeatherDataSelectAllService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("selectAllWeatherInfo : weatherlist : ");
        WeatherDAO dao = new WeatherDAO();

		float lat = Float.parseFloat(request.getParameter("lat"));
		float lon = Float.parseFloat(request.getParameter("lon"));
        
		System.out.println("WeatherDataSelectAllService : " +"첫진입");
		System.out.println("WeatherDataSelectAllService : " +lat);
		System.out.println("WeatherDataSelectAllService : " +lon);
		
		// 위경도 격자화
		CoordinateDTO coord = new CoordinateDTO();
		coord.setLat(lat);
		coord.setLng(lon);
		CoordinateConverter conver = new CoordinateConverter();
		coord = conver.change(coord);

		
		// 위경도 4자리까지만
		
		System.out.println("WeatherDataSelectAllService : " +"변환후진입");
		System.out.println("WeatherDataSelectAllService : " +coord.getLat());
		System.out.println("WeatherDataSelectAllService : " +coord.getLng());
		
		
        // float를 BigDecimal로 변환
        BigDecimal bigDecimal = BigDecimal.valueOf(coord.getLat());

        // 반올림하여 소수점 다섯 번째 자리까지 설정
        bigDecimal = bigDecimal.setScale(4, RoundingMode.HALF_UP);

        // 다시 float로 변환
        lat = bigDecimal.floatValue();
        
        // float를 BigDecimal로 변환
        BigDecimal bigDecimal2 = BigDecimal.valueOf(coord.getLng());

        // 반올림하여 소수점 다섯 번째 자리까지 설정
        bigDecimal2 = bigDecimal2.setScale(4, RoundingMode.HALF_UP);

        // 다시 float로 변환
        lon = bigDecimal2.floatValue();
		
        System.out.println("WeatherDataSelectAllService : " +"반올림진입");
        System.out.println("WeatherDataUpsertService : " +lat);
        System.out.println("WeatherDataUpsertService : " +lon);
        // DAO를 통해 데이터베이스에서 데이터를 가져옴
        List<WeatherDTO> weatherlist = dao.weatherSelectAll(lat, lon);
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
