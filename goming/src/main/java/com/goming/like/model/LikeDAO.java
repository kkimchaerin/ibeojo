package com.goming.like.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class LikeDAO {

	SqlSessionFactory factory = com.goming.like.database.SqlSessionManager.getSqlsessionFactory();
	SqlSession session;

	public int sel_like(LikeDTO dto) {
		session = factory.openSession();

		int cnt = 0;

		try {
			cnt = session.update("like_update", dto);
			if (cnt != 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 등록 실패");
		} finally {
			session.close();
		}
		return cnt;
	}

	public LikeDTO check_like(String img, String user_email) {
		session = factory.openSession();
		
		LikeDTO dto = new LikeDTO();
		dto.setUser_email(user_email);
		dto.setPost_img(img);
		
		try {
			dto = session.selectOne("com.goming.like.database.like_mapper.check_like", dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 체크 실패");
		}finally {
			session.close();
		}
		
		return dto;
	}
	
}
