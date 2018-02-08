package com.lomen.mybatisdemo.service.impl;


import com.lomen.mybatisdemo.service.CityService;

import com.lomen.mybatisdemo.ultis.DataSourceKey;
import com.lomen.mybatisdemo.user.dao.CityEntityMapper;
import com.lomen.mybatisdemo.user.model.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSourceKey
public class CityServiceImpl implements CityService{
    @Autowired
    private CityEntityMapper cityDao;

    @DataSourceKey(dataSource = "productDataSource")
    public CityEntity selectCityById(Integer cityId) {
        return cityDao.selectByPrimaryKey(cityId);
    }
}
