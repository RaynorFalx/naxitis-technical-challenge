package com.natixis.backend.config;

import com.natixis.backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                new User("user", passwordEncoder().encode("user123") , List.of(
                        new SimpleGrantedAuthority((Role.ROLE_USER.getAuthority())))
                );

        UserDetails admin =
                new User("admin", passwordEncoder().encode("admin123"), List.of(
                        new SimpleGrantedAuthority(Role.ROLE_USER.getAuthority()),
                        new SimpleGrantedAuthority(Role.ROLE_ADMIN.getAuthority())
                ));

        return new UserService(List.of(user, admin));
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
