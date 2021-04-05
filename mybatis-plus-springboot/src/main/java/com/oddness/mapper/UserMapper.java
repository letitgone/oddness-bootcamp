package com.oddness.mapper;

import com.oddness.pojo.User;

public interface UserMapper extends MyBaseMapper<User> {


    /*
        自定义findById方法
     */
    public User findById(Long id);


}
