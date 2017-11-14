package com.lanou.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户加角色信息扩展
 */
public class RoleForUser {

    private AdminInfo adminInfo;

    private List<AdminForRole> adminForRoles;

    public RoleForUser() {
    }

    public RoleForUser(AdminInfo adminInfo, List<AdminForRole> adminForRoles) {
        this.adminInfo = adminInfo;
        this.adminForRoles = adminForRoles;
    }

    public AdminInfo getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(AdminInfo adminInfo) {
        this.adminInfo = adminInfo;
    }

    public List<AdminForRole> getAdminForRoles() {
        return adminForRoles;
    }

    public void setAdminForRoles(List<AdminForRole> adminForRoles) {
        this.adminForRoles = adminForRoles;
    }

    @Override
    public String toString() {
        return "RoleForUser{" +
                "adminInfo=" + adminInfo +
                ", adminForRoles=" + adminForRoles +
                '}';
    }
}
