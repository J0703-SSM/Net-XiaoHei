package com.lanou.test;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Role;
import com.lanou.domain.RoleForUser;
import com.lanou.mapper.RoleMapper;
import com.lanou.mapper.UniversalMapper;
import com.lanou.service.UniversalService;
import com.lanou.util.HttpClientUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dllo on 17/11/11.
 */

@ContextConfiguration({"classpath:spring-config.xml"})
@WebAppConfiguration("src/main/resources")
public class UniversalTest {

    private ApplicationContext context;

    @Before
    public void init(){

        context = new ClassPathXmlApplicationContext("spring-config.xml");

    }

    @Test
    public void startSpring(){

        UniversalMapper universalMapper = context.getBean(UniversalMapper.class);

        AdminInfo info = universalMapper.findByNameOrEmail("LiHonghe");

        System.out.println(info);

    }

    @Test
    public void startService(){

        UniversalService universalService = (UniversalService) context.getBean("universalService");

        boolean b = universalService.findUser("LiHonghe");

        System.out.println(b);

    }

    @Test
    public void startRole(){

        RoleMapper roleMapper = context.getBean(RoleMapper.class);
//
//        List<AdminForRole> roleName = roleMapper.findRoleName(1);
//
//        for (AdminForRole adminForRole : roleName) {
//
//            List<Role> roleList = roleMapper.findRoleById(adminForRole.getRoleId());
//
//            Map<String,String> roles = new HashMap<String, String>();
//
//            for (Role role : roleList) {
//
//                roles.put(role.getPrivilegeId() + "",role.getPrivilegeName());
//
//            }
//
//            adminForRole.setRoles(roles);
//
//        }
////
//        System.out.println(roleName);

//        List<Role> roleById = roleMapper.findRoleById(2);
//
//        for (Role role : roleById) {
//
//            System.out.println(role);
//
//        }

//        RoleForUser roleName = roleMapper.findRoleName(2);
//
//        System.out.println(roleName.getRoleId()+":"+roleName.getRoleName());

        List<AdminForRole> customRole = roleMapper.findAllCustomRole();

//        for (AdminForRole adminForRole : customRole) {
//
//            List<Role> roleList = roleMapper.findRoleById(adminForRole.getRoleId());
//
//            Map<String,String> roles = new HashMap<String, String>();
//
//            for (Role role : roleList) {
//
//                roles.put(role.getPrivilegeId() + "",role.getPrivilegeName());
//
//            }
//
//            adminForRole.setRoles(roles);
//
//        }
//
//        System.out.println(customRole);
//
//        AdminForRole byName = roleMapper.findRoleByName("超级管理员");
//
//        System.out.println(byName);

    }

    @Test
    public void startUUID(){

        System.out.println(UUID.randomUUID().toString().replace("-",""));

    }

    @Test
    public void sendMessage() throws IOException {


//        HttpClient httpClient = new DefaultHttpClient();

        String code = HttpClientUtil.sendMessage("18604943831");

        System.out.println(code);

    }

}
