package com.goming.user.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.goming.user.database.SqlSessionManager;
import com.goming.user.model.user_DTO;

public class user_DAO {

    SqlSessionFactory factory = SqlSessionManager.getsqlSessionFactory();

    public int join(user_DTO dto) {
        SqlSession session = factory.openSession(true);
        int row = session.insert("com.goming.user.database.user_mapper.join", dto);  // ensure "join" is used
        session.close();
        return row;
    }
}
