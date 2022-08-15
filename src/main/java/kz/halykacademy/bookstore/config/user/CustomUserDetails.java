package kz.halykacademy.bookstore.config.user;

import kz.halykacademy.bookstore.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityCustomUserDetails(User user) {
        CustomUserDetails c = new CustomUserDetails();
        c.username = user.getUsername();
        c.password = user.getPassword();
        return c;
    }
    public CustomUserDetails(){};
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = authorities;
    }

    public static CustomUserDetails create(User user,List<GrantedAuthority> grantedAuthorities) {
        List<GrantedAuthority> authorities =grantedAuthorities;
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}