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

    public List<AdminInfo> findAllUser() {
        return universalMapper.findAllUser();
    }
}
