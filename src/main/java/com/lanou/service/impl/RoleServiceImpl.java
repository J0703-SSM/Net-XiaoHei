package com.lanou.service.impl;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;
import com.lanou.mapper.RoleMapper;
import com.lanou.mapper.UniversalMapper;
import com.lanou.service.RoleService;
import com.lanou.util.mail.Mail;
import com.lanou.util.mail.MailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;
import java.util.*;

/**
 * Created by dllo on 17/11/13.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UniversalMapper universalMapper;


    private List<AdminForRole> roleName;


    public RoleForUser findRoleById(AdminInfo adminInfo) {

        /* 取得角色,并生成扩展类 */
        roleName = roleMapper.findRoleName(adminInfo.getId());

        /* 调用遍历方法 */
        foreachRole();

        /* 返回封装对象 */
        return new RoleForUser(adminInfo, roleName);
    }

    public List<AdminForRole> findAllRole(){

        roleName = roleMapper.findAllCustomRole();

        /* 调用遍历方法 */
        foreachRole();

        return roleName;

    }



    public List<Role> findRoles() {
        return roleMapper.findRoles();
    }

    /* 判断角色名是否唯一 */
    public boolean findRoleByName(AdminForRole adminForRole) {

        /* 角色名去空格查询 */
        AdminForRole roleByName = roleMapper.findRoleByName(adminForRole.getRoleName().trim());

        if (roleByName == null) {

            return true;

        }

        return false;

    }

    public List<AdminInfo> deleteRole(int roleId){

        List<AdminInfo> infoList = roleMapper.findUserByRoleId(roleId);

        if(infoList.isEmpty()){

            /* 执行删除操作 */
            roleMapper.deleteRole(roleId);

            return infoList;

        }

        List<AdminInfo> userList = new ArrayList<AdminInfo>();

        for (AdminInfo adminInfo : infoList) {

            AdminInfo userById = universalMapper.findUserById(adminInfo.getId());

            userList.add(userById);

        }

        /* 返回用户信息集合 */
        return userList;

    }

    /* 插入角色至数据库 */
    public String addRole(int[] role, String roleName, String email) throws IOException, MessagingException {

        /* 封装信息 */
        AdminForRole adminForRole = new AdminForRole();

        /* 生成uuid */
        String uuid = getUID();

        /* uuid存入封装类中 */
        adminForRole.setCode(uuid);

        /* 姓名存入封装类中 */
        adminForRole.setRoleName(roleName);

        /* 插入角色信息 */
        roleMapper.addRolezInfo(adminForRole);

        /* 重新查询插入的数据 */
        adminForRole = roleMapper.findRoleByUUID(uuid);

        /* 插入不成功返回false */
        if(adminForRole == null) return null;

        /* 遍历插入权限id */
        for (int i : role) {

            /* 新建权限对象 */
            Role roleTemp = new Role();

            /* 存入对应角色的id */
            roleTemp.setRoleId(adminForRole.getRoleId());

            /* 存入权限id */
            roleTemp.setPrivilegeId(i);

            /* 插入关联数据 */
            roleMapper.addRoleId(roleTemp);

        }

        /* 查询存入的结果集 */
        List<Role> roleList = roleMapper.findRoleById(adminForRole.getRoleId());

        /* 拼接传输的信息 */
        StringBuilder msg = new StringBuilder("<p>角色名为:"+roleName+"</p><p>"+"赋予的权限有:");

        /* 遍历拼接权限 */
        for (int i = 0; i < roleList.size(); i++) {

            if(i > 0){

                msg.append(" , ");

            }

            /* 拼接权限名 */
            msg.append(roleList.get(i).getPrivilegeName());

        }

        msg.append("</p>");

        /* 执行发送邮件方法 */
        sendEmail(email,msg.toString(),uuid);

        return uuid;

    }

    /* 推送邮件方法 */
    private void sendEmail(String email,String msg,String uuid) throws IOException, MessagingException {

        Session session = MailUtils.createSession("smtp.163.com","shidifenniya@163.com","19970731SDB");

        String from = "shidifenniya@163.com";

        String to = email;

        String subject = "激活角色";

        String content = "<a href='http://localhost:8080/role/activateEmail/"+uuid+"'>" + "点此链接激活角色" + "</a>";

        String content2 = "<p>http://localhost:8080/role/activateEmail/" + uuid + "</p>";

        Mail mail = new Mail(from,to,subject, msg + content + content2);

        MailUtils.send(session,mail);

    }

    /* 调用java内机制生成uuid */
    private String getUID(){

        return UUID.randomUUID().toString().replace("-","");

    }

    /* 激活角色 */
    public void activateRole(String code){

        /* 更改状态 */
        roleMapper.activeRole(code);

    }

    private void foreachRole(){

        for (AdminForRole adminForRole : roleName) {

            List<Role> roleList = roleMapper.findRoleById(adminForRole.getRoleId());

            /* 将 List 遍历为 Map */
            Map<String,String> roles = new HashMap<String, String>();

            for (Role role : roleList) {

                roles.put(role.getPrivilegeId() + "",role.getPrivilegeName());

            }

            /* 将个人权限存入 Map 方便判断 */
            adminForRole.setRoles(roles);

        }

    }
}
