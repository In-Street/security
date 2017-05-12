package cyf.userinfo.service;

import cyf.userinfo.dao.UserInfoMapper;
import cyf.userinfo.domain.UserGrantedAuthority;
import cyf.userinfo.domain.UserInfo;
import cyf.userinfo.domain.UserLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2015/10/31.
 */
@Service
public class UserPermissionsService implements UserDetailsService {

    @Autowired
    UserInfoMapper userMapper;



    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_USER = "ROLE_USER";

    /**
     * 普通用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserLoginDetails detail = new UserLoginDetails();
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        UserGrantedAuthority uga = new UserGrantedAuthority();
        UserInfo userInfo = userMapper.getUserInfoByLoginName(username);
        uga.setAuthority(ROLE_USER);
        list.add(uga);
        detail.setUserId((long)userInfo.getId());
        detail.setUsername(username);
        detail.setPassword(userInfo.getPassword());
        detail.setAuthorities(list);

        return detail;
    }


}
