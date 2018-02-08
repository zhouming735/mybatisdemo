package com.lomen.mybatisdemo.test.service;


import com.lomen.mybatisdemo.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

//指定bean注入的配置文件
@ContextConfiguration(locations = { "classpath:application.xml" })
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class TestServiceTest {
    @Autowired
    private TestService testService;

    @Test
    public void insertTest(){
        /*TestEntity entity=new TestEntity();
        entity.setId("abcd");
        entity.setName("test");
        entity.setCreateTime(new Date());
        int num = testService.insertTest(entity);
        System.out.println("受影响的行: "+num);*/
    }


}
