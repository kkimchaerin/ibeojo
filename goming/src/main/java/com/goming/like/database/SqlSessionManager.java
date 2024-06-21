package com.goming.like.database;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionManager {

	private static SqlSessionFactory sqlSessionFactroy; // 전역변수화
	
	static {
		// static 초기화 블럭 : 프로그램이 실행되면 자동으로 실행되는 문법
		
		// DB 연결 : resource 연결(mybatis_config.xml)
		// 경로 먼저 작성
		String path = "com/goming/like/database/mybatis_config.xml";
		
		// xml 파일을 읽기 위해 getResourcesAsReader() 메소드를 사용
		try {
			Reader reader = Resources.getResourceAsReader(path);
			
			// sql factroy 생성
			sqlSessionFactroy 
			= new SqlSessionFactoryBuilder().build(reader);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlsessionFactory() {
		return sqlSessionFactroy;
	}
	
}
