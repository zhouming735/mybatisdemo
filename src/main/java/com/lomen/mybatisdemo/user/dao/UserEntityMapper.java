package com.lomen.mybatisdemo.user.dao;

import com.lomen.mybatisdemo.user.model.UserEntity;

public interface UserEntityMapper {
    /**
     *
     * @mbggenerated 2018-02-08
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int insert(UserEntity record);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int insertSelective(UserEntity record);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    UserEntity selectByPrimaryKey(Integer userId);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int updateByPrimaryKeySelective(UserEntity record);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int updateByPrimaryKey(UserEntity record);
}