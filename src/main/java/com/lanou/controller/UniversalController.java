package com.lanou.controller;

import com.lanou.domain.AdminForRole;
import com.lanou.domain.AdminInfo;
import com.lanou.domain.Netease;
import com.lanou.domain.RoleForUser;
import com.lanou.service.RoleService;
import com.lanou.service.UniversalService;
import com.lanou.util.HttpClientUtil;
import com.lanou.util.ResultMsg;
import com.lanou.util.VerifyCode;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用信息模块,登录
 */
@RequestMapping("/universal")
@Controller
public class UniversalController {

    @Qualifier("universalService")
    @Autowired
    private UniversalService universalService;

    @Qualifier("roleService")
    @Autowired
    private RoleService roleService;

    private String verifyCode;

    private String netMsgObj;

    @RequestMapping("/login")
    public String loginIn(String username, String password, String verfCode, Model model,HttpServletRequest request){

        /* 二次验证验证码 */
        if(!verfCode.trim().equalsIgnoreCase(verifyCode)){

            model.addAttribute("codeText","验证码错误");

            return "login";

        }

        /* 封装密码,用户名,和邮箱 */
        AdminInfo adminInfo = new AdminInfo(username,password,username);

        AdminInfo user = universalService.passUser(adminInfo);

        /* 查询密码错误时进行表单回填 */
        if(user == null){

            model.addAttribute("username",username);

            model.addAttribute("passwordError","密码错误");

            return "login";

        }

        RoleForUser forUser = roleService.findRoleById(user);

        /* 查询到的map进行去重 */
        Map<String, String> mapInfo = addMap(forUser);

        System.out.println(forUser);

        /* 存入权限信息以供判断 */
        request.getServletContext().setAttribute("mapInfo",mapInfo);

        request.getServletContext().setAttribute("user", forUser);
        /* 成功跳转 */
        return "index";

    }

    /* Map去重 */
    private Map<String,String> addMap(RoleForUser forUser){

        Map<String,String> mapInfo = new HashMap<String, String>();

        for (AdminForRole adminForRole : forUser.getAdminForRoles()) {

            mapInfo.putAll(adminForRole.getRoles());

        }

        return mapInfo;
    }

    @RequestMapping("/codeImg")
    public void codeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        VerifyCode code = new VerifyCode();

        BufferedImage img = code.getImage();

        verifyCode = code.getText();

        VerifyCode.output(img, response.getOutputStream());

    }

    @ResponseBody
    @RequestMapping("/passCode")
    public Map<String,Object> passCode(String codeText){

        Map<String,Object> msg = new HashMap<String,Object>();

        if(codeText.trim().equals("")){

            msg.put("codeFalse","验证码不能为空");

            return msg;

        }

        if(codeText.trim().equalsIgnoreCase(verifyCode)){

            msg.put("codeTrue","验证码通过");

        }else {

            msg.put("codeFalse","验证码错误");

        }

        return msg;

    }

    @ResponseBody
    @RequestMapping("/passUsername")
    public Map<String,Object> passUsername(String username){

//        System.out.println(username);

        Map<String,Object> msg = new HashMap<String,Object>();

        if(username.trim().equals("")){

            msg.put("userFalse","用户名不能为空");

            return msg;

        }

        boolean b = universalService.findUser(username);

        if(b){

            msg.put("userTrue","用户正确");

        }else {

            msg.put("userFalse","用户不存在");

        }

        return msg;

    }

    /* 发送手机验证码 */
    @ResponseBody
    @RequestMapping("/sendMessage")
    public Netease sendMessage(String phoneNumber) throws IOException {

        String result = HttpClientUtil.sendMessage(phoneNumber);

        /* 将字符串格式化为对象 */
        Netease netMsg = (Netease) JSONObject.toBean(JSONObject.fromObject(result), Netease.class);

        netMsgObj = netMsg.getObj();

        return netMsg;

    }

    /* 判断手机验证码 */
    @ResponseBody
    @RequestMapping("/passPhoneMsg")
    public ResultMsg passPhoneMsg(String phoneMsg){

        if(phoneMsg.trim().length() < 4 || phoneMsg.trim().length() > 5){

            return new ResultMsg(1,"验证码长度不正确");

        }

        if(!phoneMsg.trim().equals(netMsgObj)){

            return new ResultMsg(1,"验证码不匹配,请重新输入");

        }

        return new ResultMsg(0);

    }
    /* 进入index界面 */
    @RequestMapping("/intoIndex")
    public String intoIndex(){

        return "index";

    }


}
