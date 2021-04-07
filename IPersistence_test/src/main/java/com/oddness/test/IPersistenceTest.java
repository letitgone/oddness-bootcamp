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

        /**
         * Select
         */
        List<User> userList = userDao.findAll();
        for (User user1 : userList) {
            System.out.println(user1);
        }

        /**
         * Select one
         */
        User user = new User();
        user.setId(1);
        user.setUsername("lisi123");
        User user2 = userDao.findByCondition(user);
        System.out.println(user2);

        /**
         * Insert
         */
        User user3 = new User();
        user3.setUsername("铁头娃");
        user3.setPassword("123456");
        user3.setBirthday("2021-04-08");
        userDao.insertUser(user3);

        /**
         * Update
         */
        User updateUser = new User();
        updateUser.setId(4);
        updateUser.setUsername("愣头青");
        userDao.updateUser(updateUser);

        /**
         * Delete
         */
        userDao.deleteUser(9, "铁头娃");

    }
}
