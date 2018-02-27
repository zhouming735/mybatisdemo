package com.lomen.mybatisdemo.user.dao;

import com.lomen.mybatisdemo.user.model.TestEntity;

public interface TestEntityMapper {
    /**
     *
     * @mbggenerated 2018-02-08
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int insert(TestEntity record);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int insertSelective(TestEntity record);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    TestEntity selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int updateByPrimaryKeySelective(TestEntity record);

    /**
     *
     * @mbggenerated 2018-02-08
     */
    int updateByPrimaryKey(TestEntity record);
}