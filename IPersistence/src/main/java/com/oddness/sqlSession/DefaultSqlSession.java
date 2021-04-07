package com.oddness.sqlSession;

import com.oddness.pojo.Configuration;
import com.oddness.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(),
                new Class[] {mapperClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        String methodName = method.getName();
                        String className = method.getDeclaringClass().getName();

                        String statementId = className + "." + methodName;
                        Type genericReturnType = method.getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedType) {
                            List<Object> objects = selectList(statementId, args);
                            return objects;
                        }
                        String typeName = genericReturnType.getTypeName();
                        if ("int".equals(typeName)) {
                            return executeUpdate(statementId, args);
                        }
                        return selectOne(statementId, args);
                    }
                });
        return (T) proxyInstance;
    }

    @Override
    public int executeUpdate(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        int rows = simpleExecutor.queryUpdate(configuration, mappedStatement, params);
        System.out.println("==============================================UPDATE: " + rows);
        return rows;
    }

    @Override
    public int update(String statementId, Object... params) {
        return 0;
    }

    @Override
    public int delete(String statementId, Object... params) {
        return 0;
    }


}
