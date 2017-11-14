package com.lanou.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 用户信息模块
 */
@RequestMapping("/userInfo")
@Controller
public class UserInfoController {

    @RequestMapping("/into")
    public String intoUserInfo(){

        return "user/user_info";

    }

}
