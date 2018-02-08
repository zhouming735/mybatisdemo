package com.lomen.mybatisdemo.service.impl;

import com.lomen.mybatisdemo.service.TestService;
import com.lomen.mybatisdemo.ultis.DataSourceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSourceKey
public class TestServiceImpl implements TestService{
    /*@Autowired
    private TestEntityMapper testEntityMapper;


    public int insertTest(TestEntity entity) {

        return testEntityMapper.insert(entity);
    }

    public TestEntity selectById(String id) {
        return testEntityMapper.selectByPrimaryKey(id);
    }*/
}
