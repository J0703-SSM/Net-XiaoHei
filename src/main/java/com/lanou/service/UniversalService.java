package com.lanou.service;

import com.lanou.domain.AdminInfo;

import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public interface UniversalService {

    boolean findUser(String username);

    AdminInfo passUser(AdminInfo adminInfo);

    AdminInfo findUserById(int id);

    List<AdminInfo> findAllUser();

    boolean updateUser(AdminInfo adminInfo);

    boolean updatePassword(AdminInfo adminInfo);

}
