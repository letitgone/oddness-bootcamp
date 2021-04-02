package com.oddness.sqlSession;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public interface SqlSessionFactory {

    /**
     * 创建session
     *
     * @return
     */
    SqlSession openSession();

}
