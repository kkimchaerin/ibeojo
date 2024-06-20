package com.goming.mypage.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.goming.mypage.database.SqlSessionManager;


public class MyPageDAO {

	SqlSessionFactory factory = SqlSessionManager.getSqlsessionFactory();
	SqlSession session;
	
	public MyPageDTO SelectMyPageInfo(MyPageDTO dto) {
		
		session = factory.openSession();
		
		MyPageDTO m = new MyPageDTO();
		
		try {
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
	
}
