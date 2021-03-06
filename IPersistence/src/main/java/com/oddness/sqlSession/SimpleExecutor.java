package com.oddness.sqlSession;

import com.oddness.config.BoundSql;
import com.oddness.pojo.Configuration;
import com.oddness.pojo.MappedStatement;
import com.oddness.utils.GenericTokenParser;
import com.oddness.utils.ParameterMapping;
import com.oddness.utils.ParameterMappingTokenHandlers;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,
            Object... params) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(
                "=================================================SQL: " + boundSql.getSqlText());
        System.out.println(
                "===============================================PARAM: " + Arrays.toString(params));
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        int rows = 0;
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                PropertyDescriptor propertyDescriptor =
                        new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
            rows++;
        }
        System.out.println("==============================================UPDATE: " + rows);
        return (List<E>) objects;
    }

    @Override
    public int queryUpdate(Configuration configuration, MappedStatement mappedStatement,
            Object... params) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            String simpleName = parameterTypeClass.getSimpleName();
            if ("Integer".equals(simpleName) || "String".equals(simpleName)) {
                preparedStatement.setObject(i + 1, params[i]);
            } else {
                Field declaredField = parameterTypeClass.getDeclaredField(content);
                declaredField.setAccessible(true);
                Object o = declaredField.get(params[0]);
                preparedStatement.setObject(i + 1, o);
            }
        }
        System.out.println("=================================================SQL: " + boundSql.getSqlText());
        System.out.println("===============================================PARAM: " + Arrays.toString(params));
        return preparedStatement.executeUpdate();
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;

    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandlers parameterMappingTokenHandler =
                new ParameterMappingTokenHandlers();
        GenericTokenParser genericTokenParser =
                new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parseSql = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings =
                parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;

    }


}
