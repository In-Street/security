package cyf.userinfo.controller;


import cyf.userinfo.domain.UserInfo;
import cyf.userinfo.domain.UserLoginDetails;
import cyf.userinfo.service.UserInfoService;
import cyf.userinfo.service.LoginUtil;
import cyf.userinfo.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017-2-9.
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

    public static Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    LoginUtil loginUtil;



    @RequestMapping(value = "/toLogin", method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=utf-8")
    public String toLogin(){
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    @ResponseBody
    public String login(@RequestParam String loginName, @RequestParam String password, HttpSession session) {
        UserInfo userInfo = userInfoService.getUserInfoByLoginName(loginName);
        if (userInfo != null && userInfo.getStatus() == 0) {
            return "登陆失败!";
        }

        if (userInfo != null && MD5Util.getMD5Encode(password).equals(userInfo.getPassword())) {

            try {
                loginUtil.saveCredentials(userInfo, session);
                return "登录成功！";
            } catch (Exception e) {
                return "错误"+e.getLocalizedMessage();
            }
        } else {
            return "用户名或密码错误";
        }
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    @ResponseBody
    public  String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null && session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            session.setAttribute("SPRING_SECURITY_CONTEXT", null);
        }
        return "退出登录成功！";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String add(@RequestBody UserInfo userInfo, ModelMap modelMap) {
        UserLoginDetails userLoginDetails = loginUtil.getUserDetails();
        System.out.println(userLoginDetails.getAuthorities());
        System.out.println(userLoginDetails.getUsername());
        System.out.println(userLoginDetails.getPassword());

        int result = userInfoService.insertSelective(userInfo);
        System.out.println(result);
        return "添加成功";
    }


}
