package com.ll.todo20231222.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .anyRequest()
                                        .permitAll()
                )
                .headers(
                        headers ->
                                headers.frameOptions(
                                        frameOptions ->
                                                frameOptions.sameOrigin()
                                )
                )
                .csrf(
                        csrf ->
                                csrf.ignoringRequestMatchers(
                                        "/h2-console/**"
                                )
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
