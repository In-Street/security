package cyf.userinfo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by wangpeng on 2015/10/31.
 */
public class UserLoginDetails implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLoginDetails that = (UserLoginDetails) o;

        return !(getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null);

    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }


    public boolean hasRole(String role){
        for(GrantedAuthority grantedAuthority : getAuthorities()){
                if(grantedAuthority.getAuthority().equals(role)){
                    return true;
                }
        }
        return false;
    }
}
