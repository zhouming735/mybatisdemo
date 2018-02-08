package com.lomen.mybatisdemo.user.dao;

import com.lomen.mybatisdemo.user.model.UserEntity;

public interface UserEntityMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}