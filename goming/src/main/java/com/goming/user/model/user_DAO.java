package com.goming.user.model;

import java.util.List;

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

		try {

			// 첫 번째로 날짜와 시간, 좌표값이 모두 일치하는 데이터가 있는지 검사
			int count = 0;/*
							 * session.selectOne("com.goming.user.database.user_mapper.selectuser_email",
							 * dto);
							 */
			Integer emailCount = session.selectOne("com.goming.user.database.user_mapper.selectuser_email", dto);
			if (emailCount != 0) {
				count += emailCount;
				cnt = -1;
			}

			Integer nickCount = session.selectOne("com.goming.user.database.user_mapper.selectuser_nick", dto);
			if (nickCount != null) {
				count += nickCount;
				cnt = -2;
			}

			if (count == 0) {
				System.out.println("user_DAO : " + "중복값이 존재하지않습니다");
				// 검색 결과가 있으면 기존 데이터 업데이트
				cnt = session.insert("com.goming.user.database.user_mapper.join", dto);
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

	public int selectemail(String dto) {
		session = factory.openSession();

		int user = 0;

		try {
			user = session.selectOne("com.goming.user.database.user_mapper.selectuser_email", dto);
		} catch (Exception e) {
			System.out.println("이메일 조회 실패..");
			e.printStackTrace();

		} finally {
			session.close();
		}

		return user;
	}

	public int selectnick(String dto) {
		session = factory.openSession();

		int user = 0;

		try {
			user = session.selectOne("com.goming.user.database.user_mapper.selectuser_email", dto);
		} catch (Exception e) {
			System.out.println("이메일 조회 실패..");
			e.printStackTrace();

		} finally {
			session.close();
		}

		return user;
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

	// 관리자 계정
	public List<user_DTO> selectAll() {
		SqlSession session = factory.openSession();
		List<user_DTO> members = null;

		try {
			members = session.selectList("com.goming.user.database.user_mapper.getAllUsers");

			if (members != null) {
				System.out.println("전체 회원 정보 가져오기 성공");
				System.out.println(members.get(0));
				session.commit();
			} else {
				System.out.println("전체 회원 정보 가져오기 실패");
				session.rollback();
			}

		} catch (Exception e) {
			System.out.println("admin 실패");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return members;
	}

}
