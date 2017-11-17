package com.lanou.controller;

import com.lanou.domain.AdminInfo;
import com.lanou.domain.RoleForUser;
import com.lanou.service.UniversalService;
import com.lanou.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /* 判断邮箱是否重复 */
    @ResponseBody
    @RequestMapping("/sameEmail")
    public ResultMsg sameEmail(String email,HttpServletRequest request){

        Boolean b = universalService.findUserByEmail(email.trim());

        if(b){

            return new ResultMsg(0);

        }else {

            RoleForUser user = (RoleForUser) request.getServletContext().getAttribute("user");

            System.out.println(user);

            if (user.getAdminInfo().getEmail().equals(email.trim())){

                /* 如果当前域中的email与email相符,则返回true */
                return new ResultMsg(0);

            }

            return new ResultMsg(1);

        }

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
