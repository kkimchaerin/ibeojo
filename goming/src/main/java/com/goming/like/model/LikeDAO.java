package com.goming.like.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.weather.model.WeatherDTO;

public class LikeDAO
{

	SqlSessionFactory factory = com.goming.like.database.SqlSessionManager.getSqlsessionFactory();
	SqlSession session;

	public int sel_like(LikeDTO dto)
	{
		session = factory.openSession();

		int cnt = 0;

		try
		{
			cnt = session.update("like_update", dto);
			if (cnt != 0)
			{
				session.commit();
			} else
			{
				session.rollback();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("좋아요 등록 실패");
		} finally
		{
			session.close();
		}
		return cnt;
	}

	
	public List<LikeDTO> weatherSelectAll(String email)
	{
		session = factory.openSession();

		List<LikeDTO> list = null;
		try
		{
			System.out.println("LikeDAO : " + "db찾기");
			list = session.selectList("com.goming.like.database.like_mapper.selectLikedPostsByUserEmail", email);
			System.out.println("LikeDAO : list = session.selectList(\"com.goming.weather.database.weather_mapper.selectAllWeatherInfo\", email);");

		} catch (Exception e)
		{
			System.out.println("전체 조회 실패..");
			e.printStackTrace();

		} finally
		{
			session.close();
			System.out.println("LikeDAO : 닫힘성공");
		}

		return list;
	}

	public int weatherSelectAllCount(String img)
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
}
