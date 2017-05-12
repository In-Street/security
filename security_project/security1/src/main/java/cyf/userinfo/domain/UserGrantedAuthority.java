package cyf.userinfo.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by wangpeng on 2015/10/31.
 */
public class UserGrantedAuthority implements GrantedAuthority {

    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
