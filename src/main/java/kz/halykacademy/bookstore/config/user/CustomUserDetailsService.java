package kz.halykacademy.bookstore.config.user;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        List<GrantedAuthority> authorities;
        if (user.getRole().equals("admin") && !user.getIsBlocked())
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else if(user.getRole().equals("user") && !user.getIsBlocked())
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        else
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USERBLOCK"));
        return CustomUserDetails.create(user, authorities);
    }
}