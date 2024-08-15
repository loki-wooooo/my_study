package com.example.userservice.security;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserService userService, Environment env) {
        super(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    // 로그인 시 인증정보에 대한 내용을 처리함
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request
            , HttpServletResponse response
    ) throws AuthenticationException {

        try {
            // 전달시켜주고자 하는 값을 post로 받기 때문에 InputStream으로 처리를 해주면 어떻게 들어왔는지 알 수 있음
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail()
                            , creds.getPassword()
                            , new ArrayList<>()
                    )
            );
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }


    @Override
    protected void successfulAuthentication(
            HttpServletRequest request
            , HttpServletResponse response
            , FilterChain chain
            , Authentication authResult
    ) throws IOException, ServletException {
        log.debug(((User) authResult.getPrincipal()).getUsername());

        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(userName);

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
