package com.ll.todo20231222.global.security;

import com.ll.todo20231222.domain.member.member.service.AuthTokenService;
import com.ll.todo20231222.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Rq rq;
    private final AuthTokenService authTokenService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    @Order(3)
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
                .oauth2Login(
                        oauth2Login -> oauth2Login
                                .loginPage("/member/socialLogin")
                                .successHandler(customAuthenticationSuccessHandler)
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
