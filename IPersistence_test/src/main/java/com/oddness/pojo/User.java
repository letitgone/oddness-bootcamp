package com.oddness.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5736687012831339245L;

    private Integer id;

    private String username;

    private String password;

    private String birthday;

}
