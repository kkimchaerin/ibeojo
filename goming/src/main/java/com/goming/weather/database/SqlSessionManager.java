package com.goming.weather.database;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionManager {
	
	// 수정 못 하게 private 사용
	// 가지고 올 수도 없어서 getter 사용
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		// static 초기화 블럭 : 프로그램이 실행되면 자동으로 실행되는 문법
		
		// db 연결
		// db 정보는 mybatis-config.xml에 존재
		// resource 연결(mybatis-config.xml)
		// 경로 먼저 작성
		
		String path = "com/goming/weather/database/mybatis-config.xml";
		
		// xml 파일 읽기 위해 getResourceAsReader() 메소드 사용
		try {
			Reader reader = Resources.getResourceAsReader(path);
			
			// sql factory 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static SqlSessionFactory getsqlSessionFactory() {
		return sqlSessionFactory;
	}

}
