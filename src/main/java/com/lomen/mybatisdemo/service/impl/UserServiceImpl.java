package com.lomen.mybatisdemo.service.impl;

import com.lomen.mybatisdemo.service.UserService;
import com.lomen.mybatisdemo.ultis.DataSourceKey;
import com.lomen.mybatisdemo.user.dao.UserEntityMapper;
import com.lomen.mybatisdemo.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSourceKey
public class UserServiceImpl implements UserService{
    @Autowired
    private UserEntityMapper userDao;

    @DataSourceKey(dataSource = "defaultDataSource")
    public UserEntity selectUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }
/*
    public int insertList(List<User> list) {
       return  userDao.insertList(list);
    }
    */
}
