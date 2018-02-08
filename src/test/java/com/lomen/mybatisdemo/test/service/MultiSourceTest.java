package com.lomen.mybatisdemo.test.service;


import com.lomen.mybatisdemo.service.CityService;
import com.lomen.mybatisdemo.service.UserService;
import com.lomen.mybatisdemo.ultis.DataSourceContextHolder;
import com.lomen.mybatisdemo.user.model.CityEntity;
import com.lomen.mybatisdemo.user.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

//指定bean注入的配置文件
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class MultiSourceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Test
    public void test1(){

        UserEntity user = userService.selectUserById(1);
        System.out.println(user.getUserName() + ":" + user.getLastIp());

        CityEntity city=cityService.selectCityById(1);
        System.out.println(city.getCityName() + ":" + city.getId());
    }


}
