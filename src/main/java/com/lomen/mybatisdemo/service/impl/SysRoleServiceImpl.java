package com.lomen.mybatisdemo.service.impl;


import com.lomen.mybatisdemo.plugin.pagehelper.PageRowBounds;
import com.lomen.mybatisdemo.service.SysRoleService;
import com.lomen.mybatisdemo.ultis.DataSourceKey;

import com.lomen.mybatisdemo.user.dao.SysRoleMapper;
import com.lomen.mybatisdemo.user.model.CityEntity;
import com.lomen.mybatisdemo.user.model.SysRole;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSourceKey
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleDao;

    @DataSourceKey(dataSource = "defaultDataSource")
    public void test() {
       //查询第一个，使用RowBounds类型时不会查询总数
        RowBounds rowBounds = new RowBounds(0, 2);
        List<SysRole> list = roleDao.selectAll(rowBounds);

        for (SysRole role : list) {
            System.out.println("角色名： " + role.getName());
        }

        System.out.println("============使用PageRowBounds类型时查询====== ");
        //使用PageRowBounds类型时查询总数
        PageRowBounds pageRowBounds = new PageRowBounds(0, 2);
        List<SysRole> list2 = roleDao.selectAll(pageRowBounds);
        //查询总数
        System.out.println("查询总数： " + pageRowBounds.getTotal());
        for (SysRole role : list2) {
            System.out.println("角色名： " + role.getName());
        }
        //再次使用PageRowBounds类型时查询总数
        pageRowBounds = new PageRowBounds(2, 1);
        List<SysRole> list3 = roleDao.selectAll(pageRowBounds);
        //查询总数
        System.out.println("查询总数： " + pageRowBounds.getTotal());
        for (SysRole role : list3) {
            System.out.println("角色名： " + role.getName());
        }
    }
}
