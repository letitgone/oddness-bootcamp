package com.oddness.sqlSession;

import java.util.List;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public interface SqlSession {

    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 根据条件查询单个
     *
     * @param statementId
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T selectOne(String statementId, Object... params) throws Exception;


    /**
     * 为Dao接口生成代理实现类
     *
     * @param mapperClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> mapperClass);

    /**
     * executeUpdate
     *
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    int executeUpdate(String statementId, Object... params) throws Exception;

    /**
     * Update
     *
     * @param statementId
     * @param params
     * @return
     */
    int update(String statementId, Object... params);

    /**
     * delete
     *
     * @param statementId
     * @param params
     * @return
     */
    int delete(String statementId, Object... params);


}
