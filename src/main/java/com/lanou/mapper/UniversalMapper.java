package com.lanou.mapper;

import com.lanou.domain.AdminInfo;

import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public interface UniversalMapper {

    AdminInfo findByNameOrEmail(String username);

    AdminInfo passUser(AdminInfo adminInfo);

    List<AdminInfo> findAllUser();

}
