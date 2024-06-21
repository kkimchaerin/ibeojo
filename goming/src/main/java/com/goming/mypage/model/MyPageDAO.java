package com.goming.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.mypage.database.SqlSessionManager;


public class MyPageDAO {

	SqlSessionFactory factory = SqlSessionManager.getSqlsessionFactory();
	SqlSession session;
	
	// 마이 페이지 회원 정보 불러오기
	public MyPageDTO SelectMyPageInfo(MyPageDTO dto) {
		
		session = factory.openSession();
		
		MyPageDTO m = new MyPageDTO();
		
		try {
			// Email, PW 일치하는 회원 불러오기
			m = session.selectOne("selectOne", dto);
			
			if(m != null) {
				session.commit();
			}else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("정보 로딩 실패");
		}finally {
			session.close();
		}
		return m;
	}
	
	// Session에 유지되는 회원 정보(이메일)과 일치하는 회원의 게시글 불러오기
	public List<MyPageDTO> SelectMyPageImg(MyPageDTO dto) {
		session = factory.openSession();
		
		List<MyPageDTO> img_list = null;
		
		try {
			img_list = session.selectList("img_select", dto);
			
			if(img_list != null) {
				session.commit();
			}else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("이미지 로딩 실패");
		}finally {
			session.close();
		}
		return img_list;
	}
	
	
	// 회원 탈퇴
	public int deleteUser(MyPageDTO dto) {
		session = factory.openSession();
		
		int cnt = 0;
		
		try {
			cnt = session.delete("delete_user", dto);
			
			if(cnt != 0) {
				session.commit();
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			System.out.println("회원 삭제 실패");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}
}