package com.oddness.io;

import java.io.InputStream;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public class Resources {

    public static InputStream getResourceAsSteam(String path) {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;

    }

}
