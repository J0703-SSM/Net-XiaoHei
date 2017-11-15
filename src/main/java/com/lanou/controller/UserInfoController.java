package com.lanou.controller;

import com.lanou.domain.AdminInfo;
import com.lanou.domain.RoleForUser;
import com.lanou.service.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户信息模块
 */
@RequestMapping("/userInfo")
@Controller
public class UserInfoController {

    @Qualifier("universalService")
    @Autowired
    private UniversalService universalService;

    @RequestMapping("/into")
    public String intoUserInfo(){

        return "user/user_info";

    }

    @RequestMapping("/update")
    public void updateUser(AdminInfo adminInfo){

//        System.out.println(adminInfo);

        universalService.updateUser(adminInfo);

    }

    /* 进入修改密码页面 */
    @RequestMapping("/intoRewrite")
    public String intoRewrite(){

        return "user/user_modi_pwd";

    }

    /* 修改密码 */
    @RequestMapping("/rewritePassword")
    public void rewritePassword(AdminInfo adminInfo, HttpServletRequest request){

        universalService.updatePassword(adminInfo);

        /* 刷新域中的password信息 */
        AdminInfo info = universalService.findUserById(adminInfo.getId());

        /* 从域中取出user */
        RoleForUser forUser = (RoleForUser) request.getServletContext().getAttribute("user");

        /* 放入刷新后的用户信息 */
        forUser.setAdminInfo(info);

        /* 放回域中 */
        request.getServletContext().setAttribute("user", forUser);

    }

}
