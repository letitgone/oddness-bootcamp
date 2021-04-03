package com.oddness.sqlSession;

import com.oddness.pojo.Configuration;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
