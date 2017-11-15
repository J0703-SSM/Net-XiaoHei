package com.lanou.service;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface RoleService {

    RoleForUser findRoleById(AdminInfo adminInfo);

    List<AdminForRole> findAllRole();

    List<Role> findRoles();

}
