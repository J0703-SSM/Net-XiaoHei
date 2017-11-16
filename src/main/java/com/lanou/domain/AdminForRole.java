package com.lanou.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/11/13.
 */
public class AdminForRole {

    private int roleId;

    private String roleName;

    private String code;

    private boolean state;

    private Map<String,String> roles = new HashMap<String, String>();

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AdminForRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", code='" + code + '\'' +
                ", state=" + state +
                ", roles=" + roles +
                '}';
    }
}
