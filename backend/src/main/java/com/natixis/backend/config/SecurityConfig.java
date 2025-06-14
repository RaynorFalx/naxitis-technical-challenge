package com.natixis.backend.config;

import com.natixis.backend.security.CustomAuthenticationProvider;
import com.natixis.backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService inMemoryUserService;
    private final CustomAuthenticationProvider authenticationProvider;

    public SecurityConfig(UserService inMemoryUserService, CustomAuthenticationProvider authenticationProvider) {
        this.inMemoryUserService = inMemoryUserService;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .userDetailsService(inMemoryUserService)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/login", "/error").permitAll();
                    auth.requestMatchers("/home/admin").hasRole("ADMIN");
                    auth.requestMatchers("/home/user").hasRole("USER");
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }
}
