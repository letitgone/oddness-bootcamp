package com.oddness.sqlSession;

import com.oddness.config.XMLConfigBuilder;
import com.oddness.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);
        DefaultSqlSessionFactory defaultSqlSessionFactory =
                new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }

}
