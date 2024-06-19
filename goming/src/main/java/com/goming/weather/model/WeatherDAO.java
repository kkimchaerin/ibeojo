package com.goming.weather.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.weather.database.SqlSessionManager;

public class WeatherDAO
{

	// 0. Factory 가지고 오기
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

	// 중복되면 업데이트 없으면 인서트를 해주는 클래스
	public int weatherUpsert(WeatherDTO weather)
	{
		session = factory.openSession();
		int cnt = 0;
		
		System.out.println("WeatherDAO : " + "날씨 업소트 진입");
		try
		{

			System.out.println("WeatherDAO : " + "try문 진입");
			// 첫 번째로 날짜와 시간이 모두 일치하는 데이터가 있는지 검사
			int count = session.selectOne("com.goming.weather.database.weather_mapper.selectByFcstDateTime", weather);
			System.out.println("WeatherDAO : " + "session셀렉트 완료");

			if (count == 0) {
			    // 검색 결과가 없으면 새로운 데이터 삽입
			    System.out.println("WeatherDAO : " + "session인서트 실행");
			    cnt = session.insert("com.goming.weather.database.weather_mapper.insertWeatherInfo", weather);
			    System.out.println("WeatherDAO : " + "session인서트 완료");
			} else {
			    // 검색 결과가 있으면 기존 데이터 업데이트
			    System.out.println("WeatherDAO : " + "session업데이트 실행");
			    cnt = session.update("com.goming.weather.database.weather_mapper.updateWeatherInfo", weather);
			    System.out.println("WeatherDAO : " + "session업데이트 완료");
			}


			if (cnt > 0)
			{
				session.commit();
			} else
			{
				session.rollback();
			}
		} catch (Exception e)
		{
			System.out.println("게시글 등록 실패...");
			e.printStackTrace();
		} finally
		{
			session.close();
		}

		
		System.out.println("WeatherDAO : " + "게시글 업소트 성공!");
		System.out.println("WeatherDAO : " + "게시글 업소트 성공!");
		System.out.println("WeatherDAO : " + "게시글 업소트 성공!");
		return cnt;
	}

}
