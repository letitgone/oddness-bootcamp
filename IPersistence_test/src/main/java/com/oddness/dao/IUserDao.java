package com.oddness.dao;

import com.oddness.pojo.User;

import java.util.List;

/**
 * @Author ZhangGJ
 * @Date 2021/04/02 07:27
 */
public interface IUserDao {

    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    List<User> findAll() throws Exception;


    /**
     * 根据条件进行用户查询
     *
     * @param user
     * @return
     * @throws Exception
     */
    User findByCondition(User user) throws Exception;

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteUser(Integer id);


}
