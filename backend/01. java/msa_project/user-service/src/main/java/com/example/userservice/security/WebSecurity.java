package com.example.userservice.security;

import com.example.userservice.service.UserService;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private static final String[] WHITE_LIST = {
//            "/users/**",
            "/**",
            "/actuator/**", //beans, health, status etc..
    };

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        authorize -> authorize
                .requestMatchers(WHITE_LIST))
                ;

        //spring boot 2.4 -> 3.3 migration 기준으로 변경처리
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.
                userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder); // 사용자 password는 암호화 처리되어있음.

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.addFilter(getAuthenticationFilter(authenticationManager));

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        return new AuthenticationFilter(authenticationManager, userService, env);
    }




}
