package com.lanou.service;

import com.lanou.domain.AdminInfo;
import org.junit.Before;

import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public interface UniversalService {

    boolean findUser(String username);

    AdminInfo passUser(AdminInfo adminInfo);

    AdminInfo findUserById(int id);

    List<AdminInfo> findAllUser();

    Boolean findUserByEmail(String email);

    boolean updateUser(AdminInfo adminInfo);

    boolean updatePassword(AdminInfo adminInfo);

}
