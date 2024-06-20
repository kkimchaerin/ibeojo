package com.goming.weather.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.goming.weather.model.InputData;
import com.goming.weather.model.WeatherDAO;
import com.goming.weather.model.WeatherDTO;

public class WeatherDataUpsertService extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터의 위도, 경도 가져옴
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lng");

		// 위도경도로 날씨정보 리스트를 받아오는 클래스
		InputData inputData = new InputData();
		
		// 날씨정보 리스트
		List<WeatherDTO> weatherDTOs = inputData.getmasterdate(lat, lon);

		// DAO생성
		WeatherDAO dao = new WeatherDAO();

		// 날씨정보 리스트를 순회하여 하나씩 업소트를 해준다
		for (int i = 0; i < weatherDTOs.size(); i++)
		{
			System.out.println("WeatherDataUpsertService : " +i+ "번째 입력시작");
			
			int cnt = dao.weatherUpsert(weatherDTOs.get(i));
			if(cnt == 0)
			{
				System.out.println("WeatherDataUpsertService : " + "중간에 에러가 났습니다");
				break;
			}
			System.out.println("WeatherDataUpsertService : " +i+ "번째 입력후");
		}
		
		System.out.println("WeatherDAO : " + "게시글 업소트 완료!");
		System.out.println("WeatherDAO : " + "게시글 업소트 완료!");
		System.out.println("WeatherDAO : " + "게시글 업소트 완료!");

	}

}
