package io.github.lokiwooooo.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "jwt")
@Slf4j
public record JwtProperties(
        @NotBlank String secret,
        @Positive long accessTokenExpirationMs,  // 변경: expirationMs → accessTokenExpirationMs
        @Positive long refreshTokenExpirationMs, // 추가
        @NotBlank String issuer
) {
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}