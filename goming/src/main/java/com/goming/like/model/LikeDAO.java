package com.goming.like.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.database.SqlSessionManager;

public class LikeDAO {
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;
	
	// 좋아요 추가
	public int likeInsert(LikeDTO like) {
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
	
	// 좋아요 취소
    public int likeDelete(LikeDTO like) {
    	session = factory.openSession();
        int cnt = 0;
        try {
            cnt = session.delete("com.goming.like.database.like_mapper.likeDelete", like);
            session.commit();
        } catch (Exception e) {
        	System.out.println("좋아요 취소 실패");
            e.printStackTrace();
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
