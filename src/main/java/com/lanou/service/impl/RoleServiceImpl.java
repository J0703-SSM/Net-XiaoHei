package com.lanou.service.impl;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;
import com.lanou.mapper.RoleMapper;
import com.lanou.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/11/13.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

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
