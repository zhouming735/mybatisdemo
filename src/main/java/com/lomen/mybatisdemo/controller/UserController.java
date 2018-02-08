package com.lomen.mybatisdemo.controller;


import com.lomen.mybatisdemo.service.UserService;
import com.lomen.mybatisdemo.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 用户类控制器
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/hello")
    public ModelAndView getIndex(){
        ModelAndView mav = new ModelAndView("index");
        UserEntity user = userService.selectUserById(1);
        mav.addObject("user", user);
        return mav;
    }
}
