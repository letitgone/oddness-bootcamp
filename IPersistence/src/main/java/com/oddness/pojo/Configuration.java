package com.oddness.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
@Data
public class Configuration implements Serializable {

    private static final long serialVersionUID = 5819474119514077908L;

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

}
