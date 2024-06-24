package com.goming.post.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.database.SqlSessionManager;

public class PostDAO {
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

	// 포스트 업로드
	public int postInsert(PostDTO post) {
		
		session = factory.openSession();
		int cnt = 0;
		
		try {
			cnt = session.insert("com.goming.post.database.post_mapper.postInsert", post);
			
			if(cnt > 0) {
				session.commit();
			}else {
				session.rollback();
			}
			
		} catch (Exception e) {
			System.out.println("게시글 등록 실패");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}
	

	public String getidx() {
		session = factory.openSession(true);
		
		String s = null;
		
		try {
			s = session.selectOne("com.goming.post.database.post_mapper.getidx");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("인덱스 로딩 실패 ");
		}finally {
			session.close();
		}
		return s;
	}

	public String getimg() {
		session = factory.openSession();
		
		String s = null;
		
		try {
			s = session.selectOne("com.goming.post.database.post_mapper.getimg");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("이미지 로딩 실패");
		}finally {
			session.close();
		}
		return s;
	}
	
	public int updateimg(String post_img, int post_idx) {
		session = factory.openSession();
		int cnt = 0;
		
		PostDTO dto = new PostDTO(post_idx, post_img);
		
		
		try {
			cnt = session.update("com.goming.post.database.post_mapper.updateImg", dto);
			if(cnt > 0) {
				session.commit();
			}else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("사진 수정 실패");
		}finally {
			session.close();
		}
		return cnt;
	}
	
}
