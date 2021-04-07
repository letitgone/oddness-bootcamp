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
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User user = new User();
        user.setUsername("铁头娃");
        user.setBirthday("2021-04-07");
        user.setPassword("123456");

        /**
         * Select
         */
        List<User> userList = userDao.findAll();
        User user1 = userDao.findByCondition(user);

        /**
         * Insert
         */
        userDao.insertUser(user);

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setUsername("愣头青");
        /**
         * Update
         */
        userDao.updateUser(user);

        /**
         * Delete
         */
        userDao.deleteUser(user.getId());

    }
}
