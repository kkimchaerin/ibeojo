package com.goming.post.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.database.SqlSessionManager;

public class PostDAO {

	// 포스트 업로드
	public int postInsert(PostDTO post) {
		SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
		SqlSession session;

		session = factory.openSession();
		int cnt = 0;

		try {
			cnt = session.insert("com.goming.post.database.post_mapper.postInsert", post);

			if (cnt > 0) {
				session.commit();
			} else {
				session.rollback();
			}

		} catch (Exception e) {
			System.out.println("게시글 등록 실패");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}


}
