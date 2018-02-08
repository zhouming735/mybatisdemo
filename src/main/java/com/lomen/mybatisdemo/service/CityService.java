package com.lomen.mybatisdemo.service;


import com.lomen.mybatisdemo.user.model.CityEntity;

import java.util.List;

public interface CityService {
    CityEntity selectCityById(Integer cityId);

}
