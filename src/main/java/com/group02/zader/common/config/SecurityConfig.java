package com.group02.zader.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/index", "/css/**", "/js/**", "/images/**").permitAll() // Cho phép truy cập trang chủ và file tĩnh
                .anyRequest().authenticated() // Các trang khác phải đăng nhập
            )
            .formLogin((form) -> form
                .loginPage("/login") // Sau này sẽ làm trang login custom
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }
}