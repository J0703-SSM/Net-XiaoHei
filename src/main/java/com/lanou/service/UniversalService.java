package com.lanou.service;

import com.lanou.domain.AdminInfo;

import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public interface UniversalService {

    boolean findUser(String username);

    AdminInfo passUser(AdminInfo adminInfo);

    List<AdminInfo> findAllUser();

}
