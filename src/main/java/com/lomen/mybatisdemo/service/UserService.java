package com.lomen.mybatisdemo.service;


import com.lomen.mybatisdemo.user.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity selectUserById(Integer userId);
    //int insertList(List<User> list);
}
