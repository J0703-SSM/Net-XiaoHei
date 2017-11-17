package com.lanou.service;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface RoleService {

    RoleForUser findRoleById(AdminInfo adminInfo);

    List<AdminForRole> findAllRole();

    List<Role> findRoles();

    boolean findRoleByName(AdminForRole adminForRole);

    String addRole(int[] role,String roleName,String email) throws IOException, MessagingException;

    void activateRole(String code);

    List<AdminInfo> deleteRole(int roleId);

}
