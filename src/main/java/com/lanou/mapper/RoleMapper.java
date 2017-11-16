package com.lanou.mapper;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface RoleMapper {

    AdminForRole findRoleByName(String roleName);

    List<Role> findRoleById(int id);

    List<AdminForRole> findRoleName(int id);

    List<AdminForRole> findAllCustomRole();

    List<Role> findRoles();

    int addRolezInfo(AdminForRole adminForRole);

    int addRoleId(Role role);

    int activeRole(String code);
}
