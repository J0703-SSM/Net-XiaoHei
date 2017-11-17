package com.lanou.service.impl;

import com.lanou.domain.AdminInfo;
import com.lanou.mapper.UniversalMapper;
import com.lanou.service.UniversalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
@Service("universalService")
public class UniversalServiceImpl implements UniversalService {

    @Resource
    private UniversalMapper universalMapper;

    public boolean findUser(String username) {

        AdminInfo adminInfo = universalMapper.findByNameOrEmail(username);

        if(adminInfo != null){

            return true;

        } else {

            return false;

        }

    }

    public AdminInfo passUser(AdminInfo adminInfo) {

        return universalMapper.passUser(adminInfo);

    }

    public AdminInfo findUserById(int id) {

        return universalMapper.findUserById(id);

    }

    public List<AdminInfo> findAllUser() {
        return universalMapper.findAllUser();
    }

    public Boolean findUserByEmail(String email) {

        AdminInfo byEmail = universalMapper.findUserByEmail(email);

        if(byEmail == null){

            return true;

        }

        return false;
    }

    public boolean updateUser(AdminInfo adminInfo) {

        int count = universalMapper.updateUser(adminInfo);

        if(count > 0){

            return true;

        }

        return false;
    }

    public boolean updatePassword(AdminInfo adminInfo) {

        int count = universalMapper.updatePassword(adminInfo);

        if(count > 0){

            return true;

        }

        return false;
    }

}
