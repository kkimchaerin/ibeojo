package com.goming.weather.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.weather.database.SqlSessionManager;


public class WeatherDAO
{

	// 0. Factory 가지고 오기
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

    public static float roundToFourDecimalPlaces(float value) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(4, RoundingMode.HALF_UP);  // 4자리까지 반올림
        return bd.floatValue();
    }
	
	// 중복되면 업데이트 없으면 인서트를 해주는 클래스
	public int weatherUpsert(WeatherDTO weather)
	{
		session = factory.openSession();
		int cnt = 0;
		
		System.out.println("WeatherDAO : " + "날씨 업소트 진입");
		try
		{

//			session.update("com.goming.weather.database.weather_mapper.dropIndexUcFcstDatetime");
//			session.update("com.goming.weather.database.weather_mapper.addUniqueConstraintUcFcstDatetimeLatLon");
			
			System.out.println("WeatherDAO : " + "try문 진입");
			System.out.println("WeatherDAO : " +weather.getFcstDate()+ "날짜");
			System.out.println("WeatherDAO : " +weather.getFcstTime()+ "시간");
			weather.setLat(roundToFourDecimalPlaces(weather.getLat()));
			System.out.println("WeatherDAO : " +weather.getLat()+ "위도");
			weather.setLon(roundToFourDecimalPlaces(weather.getLon()));
			System.out.println("WeatherDAO : " +weather.getLon()+ "경도");
			// 첫 번째로 날짜와 시간, 좌표값이 모두 일치하는 데이터가 있는지 검사
			int count = session.selectOne("com.goming.weather.database.weather_mapper.selectByFcstDateTime", weather);
			System.out.println("WeatherDAO : " + "session셀렉트 완료");

			System.out.println("WeatherDAO : " +count+ "이게 카운트값");
			
			if (count != 0) {
				// 검색 결과가 있으면 기존 데이터 업데이트
				System.out.println("WeatherDAO : " + "session업데이트 실행");
				cnt = session.update("com.goming.weather.database.weather_mapper.updateWeatherInfo", weather);
				System.out.println("WeatherDAO : " + "session업데이트 완료");
			} else {
				// 검색 결과가 없으면 새로운 데이터 삽입
				System.out.println("WeatherDAO : " + "session인서트 실행");
				cnt = session.insert("com.goming.weather.database.weather_mapper.insertWeatherInfo", weather);
				System.out.println("WeatherDAO : " + "session인서트 완료");
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
			System.out.println("WeatherDAO : 닫힘성공");
		}

		

		return cnt;
	}

	// 게시글 전체 조회 기능
	public List<WeatherDTO> weatherSelectAll(float lat, float lon)
	{
		System.out.println("WeatherDAO : ");
		session = factory.openSession();
		System.out.println("WeatherDAO : " + "session = factory.openSession();");
		List<WeatherDTO> list = null;
		System.out.println("WeatherDAO : " + "List<WeatherDTO> list = null;");

		Map<String, Object> params = new HashMap<>();
		params.put("lat", lat);
		params.put("lon", lon);
		System.out.println("WeatherDAO : lat" + lat);
		System.out.println("WeatherDAO : lon" + lon);
		
		try
		{
			System.out.println("WeatherDAO : " + "db찾기");
			list = session.selectList("com.goming.weather.database.weather_mapper.selectAllWeatherInfo", params);
			System.out.println("WeatherDAO : " + "list = session.selectList(\"com.goming.weather.database.weather_mapper.selectAllWeatherInfo\");");
		
		}
		catch (Exception e)
		{
			 System.out.println("전체 조회 실패..");
			 e.printStackTrace();
			 
		} finally
		{
			session.close();
			System.out.println("WeatherDAO : 닫힘성공");
		}

		return list;
		
	}// boardSelectAll end

	
}
