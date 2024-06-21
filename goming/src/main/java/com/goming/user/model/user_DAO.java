package com.goming.user.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.goming.user.database.SqlSessionManager;
import com.goming.user.model.user_DTO;

public class user_DAO {

	// 0. Factory 가지고 오기
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

	// 회원가입
	public int join(user_DTO dto) {
		session = factory.openSession();
		int cnt = 0;

		System.out.println("user_DAO : " + "날씨 업소트 진입");
		try {

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

			if (count == 0) {
				System.out.println("user_DAO : " + "중복값이 존재하지않습니다");
				// 검색 결과가 있으면 기존 데이터 업데이트
				cnt = session.insert("com.goming.user.database.user_mapper.join", dto);
			} else {

			}

			if (cnt > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			System.out.println("user_DAO : " + "가입 실패...");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}

	// 로그인
	public user_DTO login(user_DTO dto) {

		// 1. factory 내부의 Session 객체 열어주기
		SqlSession session = factory.openSession(true);

		// sql 연결
		user_DTO m = session.selectOne("com.goming.user.database.user_mapper.login", dto);

		// session 자원 반납
		session.close();
		// controller에 결과값 보내주기
		return m;

	}

	// 아이디 찾기
	public String findIdByNick(String nick) {
		SqlSession session = factory.openSession(true);
		String foundId = null;

		try {
			foundId = session.selectOne("findIdByNick", nick);
		} finally {
			session.close();
		}

		return foundId;
	}

	public boolean updatePasswordByEmail(String user_email, String user_pw) {
		SqlSession session = factory.openSession(true);
		boolean isUpdated = false;

		try {
			int result = session.update("updatePasswordByEmail", new user_DTO(user_email, user_pw));
			if (result > 0) {
				isUpdated = true;
			}
		} finally {
			session.close();
		}

		return isUpdated;
	}
}
