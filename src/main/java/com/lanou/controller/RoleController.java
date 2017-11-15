package com.lanou.controller;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;
import com.lanou.service.RoleService;
import com.lanou.service.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Qualifier("universalService")
    @Autowired
    private UniversalService universalService;

    @Qualifier("roleService")
    @Autowired
    private RoleService roleService;
    private List<RoleForUser> userList;

    @RequestMapping("/roleList")
    public String intoRoleList(Model model){

        List<AdminForRole> roles = roleService.findAllRole();

        model.addAttribute("roles", roles);

        return "/role/role_list";

    }

    @RequestMapping("/addRole")
    public String intoAddRole(Model model){

        List<Role> roleList = roleService.findRoles();

        model.addAttribute("roleList",roleList);

        System.out.println(roleList);

        return "role/role_add";

    }

    @RequestMapping("/adminList")
    public String intoAdmin(Model model){

        findRole();

        model.addAttribute("userList", userList);

        return "/admin/admin_list";

    }



    /* 遍历所有权限信息方法 */
    private void findRole(){

        List<AdminInfo> users = universalService.findAllUser();

        userList = new ArrayList<RoleForUser>();

        for (AdminInfo user : users) {

            userList.add(roleService.findRoleById(user));

        }

    }

}
