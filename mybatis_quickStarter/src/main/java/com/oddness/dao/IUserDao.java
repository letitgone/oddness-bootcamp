package com.oddness.dao;

import com.oddness.pojo.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {

    //查询所有用户
    public List<User> findAlls() throws IOException;

    //多条件组合查询：演示if
    public List<User> findByConditions(User user);


    //多值查询：演示foreach
    public List<User> findByIds(int[] ids);


}
