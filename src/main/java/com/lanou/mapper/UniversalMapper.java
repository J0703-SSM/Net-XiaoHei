package com.lanou.mapper;

import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;

import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public interface UniversalMapper {

    AdminInfo findByNameOrEmail(String username);

    AdminInfo passUser(AdminInfo adminInfo);

    AdminInfo findUserById(int id);

    AdminInfo findUserByEmail(String email);

    List<AdminInfo> findAllUser();

    int updateUser(AdminInfo adminInfo);

    int updatePassword(AdminInfo adminInfo);

}
