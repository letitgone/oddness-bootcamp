package com.oddness.test;

import com.oddness.dao.IUserDao;
import com.oddness.io.Resources;
import com.oddness.pojo.User;
import com.oddness.sqlSession.SqlSession;
import com.oddness.sqlSession.SqlSessionFactory;
import com.oddness.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        User user = new User();
//        user.setId(1);
//        user.setUsername("tom");
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);
//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User user1 : users) {
//            System.out.println(user1);
//        }
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
    }
}
