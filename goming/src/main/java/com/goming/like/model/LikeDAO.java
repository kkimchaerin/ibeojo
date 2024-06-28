package com.goming.like.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.database.SqlSessionManager;

public class LikeDAO
{
	SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();
	SqlSession session;

	// 좋아요 추가
	public int likeInsert(LikeDTO like)
	{
		session = factory.openSession();
		int cnt = 0;

		try
		{
			cnt = session.insert("com.goming.like.database.like_mapper.likeInsert", like);

			if (cnt > 0)
			{
				session.commit();
			} else
			{
				session.rollback();
			}

		} catch (Exception e)
		{
			System.out.println("좋아요 등록 실패");
			e.printStackTrace();
		} finally
		{
			session.close();
		}

		return cnt;
	}

	public int LikeSelectAllCount(String img)
	{
		session = factory.openSession();

		int cnt = 0;
		try
		{
			System.out.println("LikeDAO : " + "db찾기");
			cnt = session.selectOne("com.goming.like.database.like_mapper.countLikesByPostIdx", img);
			System.out.println("LikeDAO : cnt = " + cnt);

		} catch (Exception e)
		{
			System.out.println("전체 조회 실패..");
			e.printStackTrace();

		} finally
		{
			session.close();
			System.out.println("LikeDAO : 닫힘성공");
		}

		return cnt;
	}

	// 좋아요 취소
	public int likeDelete(LikeDTO like)
	{
		session = factory.openSession();
		int cnt = 0;
		try
		{
			cnt = session.delete("com.goming.like.database.like_mapper.likeDelete", like);
			session.commit();
		} catch (Exception e)
		{
			System.out.println("좋아요 취소 실패");
			e.printStackTrace();
		}
		return cnt;
	}

	public boolean isLiked(LikeDTO like)
	{
		int count = 0;

		SqlSession session = null;
		try
		{
			session = factory.openSession();
			Integer result = session.selectOne("com.goming.like.database.like_mapper.likeCheck", like);
			if (result != null)
			{
				count = result;
			}
		} catch (Exception e)
		{
			System.out.println("좋아요 불러오기 실패");
			e.printStackTrace();
		} finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		return count > 0;
	}

	public int likeToggle(String post_img, String user_email)
	{
		session = factory.openSession();
		int cnt = 0;
		int count = 0;

		try
		{
			Map<String, Object> params = new HashMap<>();
			params.put("user_email", user_email);
			params.put("post_img", post_img);

			System.out.println("params.put(\"user_email\", user_email);" + user_email);
			System.out.println("params.put(\"post_img\", post_img);" + post_img);
			
			// MyBatis를 사용하여 좋아요 여부 확인
			count = session.selectOne("com.goming.like.database.like_mapper.checkLikepostuser", params);
			System.out.println("count = session.selectOne(\"com.goming.like.database.like_mapper.checkLikepostuser\", params); : "+count);
			
			System.out.println("params : " +params.get("post_img"));
			System.out.println("params : " +params.get("user_email"));
			if (count > 0)
			{
				// 좋아요가 이미 등록된 경우, 좋아요 삭제 처리
				cnt = session.delete("com.goming.like.database.like_mapper.removeLike", params);
				System.out.println("삭제처리 : " + cnt);
			} else
			{
				// 좋아요가 등록되지 않은 경우, 좋아요 추가 처리
				cnt = session.insert("com.goming.like.database.like_mapper.addLike", params);
				System.out.println("추가처리 : " + cnt);
			}

			if (cnt > 0)
			{
				session.commit();
			} else
			{
				session.rollback();
			}

		} catch (Exception e)
		{
			System.out.println("좋아요 등록/삭제 실패");
			e.printStackTrace();
		} finally
		{
			session.close();
		}

		return cnt;
	}

	public int likeToggleCount(String post_img, String user_email)
	{
		session = factory.openSession();
		int cnt = 0;

		try
		{
			Map<String, Object> params = new HashMap<>();
			params.put("user_email", user_email);
			params.put("post_img", post_img);

			System.out.println("likeToggleCount : " + user_email);
			System.out.println("likeToggleCount : " + post_img);
			
			// MyBatis를 사용하여 좋아요 여부 확인
			cnt = session.selectOne("com.goming.like.database.like_mapper.checkLikepostuser", params);



		} catch (Exception e)
		{
			System.out.println("좋아요 등록/삭제 실패");
			e.printStackTrace();
		} finally
		{
			session.close();
		}

		return cnt;
	}

}
