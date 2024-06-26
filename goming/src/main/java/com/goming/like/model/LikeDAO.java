package com.goming.like.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.database.SqlSessionManager;

public class LikeDAO {
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;
	
	// 좋아요 업로드
	public int likeInsert(LikeDTO like) {
		SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
		SqlSession session;

		session = factory.openSession();
		int cnt = 0;

		try {
			cnt = session.insert("com.goming.like.database.like_mapper.likeInsert", like);

			if (cnt > 0) {
				session.commit();
			} else {
				session.rollback();
			}

		} catch (Exception e) {
			System.out.println("좋아요 등록 실패");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}
	
	

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

}
