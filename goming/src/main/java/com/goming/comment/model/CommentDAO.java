package com.goming.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.weather.database.SqlSessionManager;
import com.goming.weather.model.WeatherDTO;

public class CommentDAO {

	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;
	
	public List<WeatherDTO> getWeather(CommentDTO d) {
		session = factory.openSession();
		
		List<WeatherDTO> dto = null; 
		
		try {
			dto = session.selectList("com.goming.comment.database.comment_mapper.weatherinfo", d);
			if(dto != null) {
				System.out.println("날씨 위치정보 로딩 성공");
			}else {
				System.out.println("날씨 위치정보 로딩 실패(sql null)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("날씨 위치정보 로딩 실패");
		}finally {
			session.close();
		}
		
		return dto;
	}
	
}
