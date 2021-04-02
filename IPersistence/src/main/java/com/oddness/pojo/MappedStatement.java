package com.oddness.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
@Data
public class MappedStatement implements Serializable {

    private static final long serialVersionUID = 125824985379818646L;

    private String id;

    private String resultType;

    private String parameterType;

    private String sql;
}
