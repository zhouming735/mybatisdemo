package com.lomen.mybatisdemo.test.service;


import com.lomen.mybatisdemo.service.UserService;
import com.lomen.mybatisdemo.test.SpringTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

//指定bean注入的配置文件
@ContextConfiguration(locations = { "classpath:application.xml" })
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    //@Autowired
    //private UserService userService;

    @Test
    public void selectUserByIdTest(){
        //User user = userService.selectUserById(1);
        //System.out.println(user.getUserName() + ":" + user.getUserPassword());
    }
    @Test
    public void insertList(){
       /* List<User> list=new ArrayList<User>();
        for(int i=0;i<5;i++) {
            User user = new User();
            user.setUserName("admin"+i);
            user.setUserPassword("1234");
            list.add(user);
        }*/
        //int a=userService.insertList(list);

        //System.out.println("插入行数："+a);
    }

}
