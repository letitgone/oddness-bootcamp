package com.oddness.sqlSession;

import com.oddness.pojo.Configuration;
import com.oddness.pojo.MappedStatement;

import java.util.List;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public interface Executor {

    public <E> List<E> query(Configuration configuration,MappedStatement mappedStatement,Object... params) throws Exception;

}
