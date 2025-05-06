package io.github.lokiwooooo.config;

import io.github.lokiwooooo.domain.token.repository.TokenRepository;
import io.github.lokiwooooo.handler.JwtAccessDeniedHandler;
import io.github.lokiwooooo.handler.JwtAuthenticationEntryPoint;
import io.github.lokiwooooo.jwt.JwtAuthenticationFilter;
import io.github.lokiwooooo.jwt.JwtTokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class SecurityConfig {

    JwtTokenProvider jwtTokenProvider;
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    JwtAccessDeniedHandler jwtAccessDeniedHandler;
    TokenRepository tokenRepository;

    @Value("${server.x-frame-option}")
    @NonFinal
    Boolean isXFrameOption;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                // 인증 인가 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //permit 처리
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers(HttpMethod.GET, httpGetPermitAllURL()).permitAll()
                        .requestMatchers(HttpMethod.POST, httpPostPermitAllURL()).permitAll()
                        .requestMatchers(HttpMethod.PUT, httpPutPermitAllURL()).permitAll()
                        .requestMatchers(HttpMethod.DELETE, httpDeletePermitAllURL()).permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider, tokenRepository),
                        UsernamePasswordAuthenticationFilter.class
                );

        // X-Frame-Options 설정
        if (isXFrameOption) {
            http.headers(headers -> headers
                    .frameOptions(frame -> frame.sameOrigin())
            );
        } else {
            http.headers(headers -> headers
                    .frameOptions(frame -> frame.disable())
            );
        }

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Content-Disposition");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private String[] httpGetPermitAllURL() {
        String[] urls = {
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/",
                "/index.html",
                "/static/**",
                "/js/**",
                "/css/**",
                "/img/**",
                "/favicon.ico",
                "/{path:[^\\.]*}"
        };
        return urls;
    }

    private String[] httpPostPermitAllURL() {
        String[] urls = {
                "/rest/kit/v1/auth/login",
                "/rest/kit/v1/token/refresh"
        };
        return urls;
    }

    private String[] httpPutPermitAllURL() {
        String[] urls = {
        };
        return urls;
    }

    private String[] httpDeletePermitAllURL() {
        String[] urls = {
        };
        return urls;
    }

}