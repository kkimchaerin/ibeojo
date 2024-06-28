package com.goming.post.model;

import java.util.List;

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
		List<PostDTO> postList = null;

		try {
			// MyBatis 매퍼 호출
			postList = session.selectList("com.goming.post.database.post_mapper.getPostsByFilters", filter);
			/* System.out.println("필터링DB: " + postList.get(0).toString()); */
			// System.out.println("필터링DB: " + postList.get(0).getComment());
		} catch (Exception e) {
			System.out.println("호출 실패");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return postList;
	}

	public String getidx() {
		session = factory.openSession(true);

		String s = null;

		try {
			s = session.selectOne("com.goming.post.database.post_mapper.getidx");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("인덱스 로딩 실패 ");
		} finally {
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
		} finally {
			session.close();
		}
		return s;
	}

	public int update(int idx, String img) {
		session = factory.openSession();

		PostDTO dto = new PostDTO();

		dto.setPost_idx(idx);
		dto.setPost_img(img);

		int cnt = 0;

		try {
			cnt = session.update("com.goming.post.database.post_mapper.updateImg", dto);

			if (cnt > 0) {
				System.out.println("업데이트 성공");
				session.commit();
			} else {
				System.out.println("업데이트 실패");
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("업데이트 로딩 실패");
		} finally {
			session.close();
		}

		return cnt;
	}
	
	public int deletePost(String img) {
		session = factory.openSession();
		
		int cnt = 0;
		
		try {
			cnt = session.delete("com.goming.post.database.post_mapper.deletecas", img);
			cnt = session.delete("com.goming.post.database.post_mapper.deleteImg", img);
			if(cnt > 0) {
				session.commit();
				System.out.println("게시글 삭제 성공");
			}else {
				session.rollback();
				System.out.println("게시글 삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 삭제 로딩 실패");
		}finally {
			session.close();
		}
		
		
		return cnt;
	}
	
	
}
