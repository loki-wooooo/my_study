package io.github.lokiwooooo.jwt;

import io.github.lokiwooooo.config.JwtProperties;
import io.github.lokiwooooo.domain.token.entity.Token;
import io.github.lokiwooooo.domain.token.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    JwtTokenProvider jwtTokenProvider;
    TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain
    ) throws ServletException, IOException {

        String token = resolveToken(request);
        if (token != null) {

            // Step,1 만료 테이블 확인
            try {
                Token tokenInfo = tokenRepository.findByIsUseAndAccessToken(true, token).orElse(null);
                if (tokenInfo == null) {
                    throw new Exception("Token has been expired by logout");
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }

            // Step.2 토큰 유효성 검사
            if (jwtTokenProvider.validateToken(token)) {
                SecurityContextHolder.getContext().setAuthentication(
                        jwtTokenProvider.getAuthentication(token)
                );
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.HEADER);
        return (header != null && header.startsWith(JwtProperties.TOKEN_PREFIX))
                ? header.substring(7)
                : null;
    }

}