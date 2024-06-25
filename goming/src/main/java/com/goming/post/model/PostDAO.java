package com.goming.post.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.database.SqlSessionManager;

public class PostDAO {
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

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
	
    // 필터에 따라 포스트 이미지 리스트 가져오기
	public List<PostDTO> getPostsByFilters(PostDTO filter) {
	    SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	    SqlSession session = factory.openSession();
	    List<PostDTO> postImages = null;

	    try {
	        // MyBatis 매퍼 호출
	        postImages = session.selectList("com.goming.post.database.post_mapper.getPostsByFilters", filter);
	        System.out.println("Filtered Posts from DB: " + postImages);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return postImages;
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
