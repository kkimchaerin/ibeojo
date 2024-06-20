package com.goming.user.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.goming.user.database.SqlSessionManager;
import com.goming.user.model.user_DTO;

public class user_DAO
{

	// 0. Factory 가지고 오기
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

	public int join(user_DTO dto)
	{
		session = factory.openSession();
		int cnt = 0;

		System.out.println("user_DAO : " + "날씨 업소트 진입");
		try
		{

			System.out.println("user_DAO : " + "try문 진입");
			// 첫 번째로 날짜와 시간, 좌표값이 모두 일치하는 데이터가 있는지 검사
			int count = session.selectOne("com.goming.user.database.user_mapper.selectuser_email", dto);

	        Integer emailCount = session.selectOne("com.goming.user.database.user_mapper.selectuser_email", dto);
	        if (emailCount != null) {
	            count += emailCount;
	            cnt = -1;
	        }
	        System.out.println("user_DAO : " + "이메일 체크 : " + emailCount);

	        Integer nickCount = session.selectOne("com.goming.user.database.user_mapper.selectuser_nick", dto);
	        if (nickCount != null) {
	            count += nickCount;
	            cnt = -2;
	        }
	        System.out.println("user_DAO : " + "이메일 체크" + nickCount);
	        
			if (count == 0)
			{
				System.out.println("user_DAO : " + "중복값이 존재하지않습니다");
				// 검색 결과가 있으면 기존 데이터 업데이트
				cnt = session.insert("com.goming.user.database.user_mapper.join", dto);
			}
			else
			{
				
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
			System.out.println("user_DAO : " + "가입 실패...");
			e.printStackTrace();
		} finally
		{
			session.close();
		}

		return cnt;
	}
//	public int join(user_DTO dto)
//	{
//		SqlSession session = factory.openSession(true);
//		int row = session.insert("com.goming.user.database.user_mapper.join", dto); // ensure "join" is used
//		session.close();
//		return row;
//	}
}
