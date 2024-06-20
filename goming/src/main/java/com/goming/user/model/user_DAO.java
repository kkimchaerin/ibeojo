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
		SqlSession session = factory.openSession(true);
		int row = session.insert("com.goming.user.database.user_mapper.join", dto); // ensure "join" is used
		session.close();
		return row;
	}
//	public int join(user_DTO dto)
//	{
//		SqlSession session = factory.openSession(true);
//		int row = session.insert("com.goming.user.database.user_mapper.join", dto); // ensure "join" is used
//		session.close();
//		return row;
//	}
}
