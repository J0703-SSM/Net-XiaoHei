package com.lanou.controller;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;
import com.lanou.service.RoleService;
import com.lanou.service.UniversalService;
import com.lanou.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @ResponseBody
    @RequestMapping("/passRoleName")
    public ResultMsg passRoleName(AdminForRole adminForRole){

        if(adminForRole.getRoleName().trim().equals("")){

            return new ResultMsg(1,"角色名不能为空");

        }

        if(adminForRole.getRoleName().trim().length() > 10){

            return new ResultMsg(1,"角色名长度不能大于10个字符");

        }

        boolean b = roleService.findRoleByName(adminForRole);

        if(b){

            return new ResultMsg(0,"角色名可以使用");

        }else {

            return new ResultMsg(1,"角色名已被使用");

        }

    }

    @RequestMapping("/addRoleNexus")
    public String addRoleNexus(int[] role, String roleName, String email, Model model, HttpServletRequest request) throws IOException, MessagingException, InterruptedException {

        /* 进行插入操作 */
        String uuid = roleService.addRole(role, roleName, email);

        /* 执行确认完成后 */
        if(uuid != null){

            /* 将激活码存入域中 */
            request.getServletContext().setAttribute("activateRole", uuid);

            /* 延时休眠1.5秒 */
            Thread.sleep(1500);

            /* 返回主页 */
            return "index";

        }

        /* 返回添加界面 */
        return "role/role_add";

    }

    /* 删除角色 */
    @ResponseBody
    @RequestMapping("/deleteRole")
    public ResultMsg deleteRole(int roleId){

        List<AdminInfo> infoList = roleService.deleteRole(roleId);

        if(infoList.isEmpty()){

            return new ResultMsg(0,"删除成功");

        }

        StringBuilder msg = new StringBuilder("<p>操作失败!有");

        /* 拼接使用人数 */
        msg.append(infoList.size()).append("个管理员正在使用该角色</p><p>管理员账户为:");

        /* 拼接账户信息 */
        for (AdminInfo adminInfo : infoList) {

            msg.append(adminInfo.getAdmin_code()).append("  ");

        }

        msg.append("</p>");

        return new ResultMsg(1,msg.toString());

    }

    /* 邮件确认操作 */
    @RequestMapping("/activateEmail/{code}")
    public String activate(@PathVariable String code, HttpServletRequest request, Model model){

        String actUUID = (String) request.getServletContext().getAttribute("activateRole");

        if(actUUID != null && actUUID.equals(code)){

            roleService.activateRole(code);

            request.getServletContext().removeAttribute("activateRole");

            model.addAttribute("msg","激活成功!返回原页面即可完成添加操作");

            return "/msg";

        }

        model.addAttribute("msg","这可能是一个失效的链接");

        return "/msg";

    }
    /* 进入修改页面 */
    @RequestMapping("/intoUpdateRole")
    public String intoUpdateRole(){

        return "role/role_modi";

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
