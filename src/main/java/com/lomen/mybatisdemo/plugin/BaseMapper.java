package com.lomen.mybatisdemo.plugin;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T, PK extends Serializable> {

    int deleteByPrimaryKey(PK pk);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(PK pk);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
}
