package cyf.userinfo.service;

import cyf.userinfo.domain.UserInfo;
import cyf.userinfo.domain.UserLoginDetails;
import cyf.userinfo.service.UserPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017-2-16.
 */
@Service
public class LoginUtil {


    @Autowired
    UserPermissionsService userPermissionsService;


    /**
     * 将认证信息保存到session中，供springsecurity使用
     * @param userInfo
     * @param session
     */
    public  void saveCredentials(UserInfo userInfo, HttpSession session){
        UserDetails userDetails = userPermissionsService.loadUserByUsername(userInfo.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT",securityContext);
    }


    /**
     * 获取用户登陆后的认证信息
     * @return
     */
    public UserLoginDetails getUserDetails(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if(authentication != null){
            return (UserLoginDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 获取登陆用户的用户详情
     * @return
     */
    /*protected UserInfo getLoginUserInfo(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if(authentication != null){
            UserLoginDetails userLoginDetails =  (UserLoginDetails) authentication.getPrincipal();
            return userInfoService.getUserInfoById(userLoginDetails.getUserId());
        }
        return null;
    }*/



}
