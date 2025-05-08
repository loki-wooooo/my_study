package io.github.lokiwooooo.jwt;

import io.github.lokiwooooo.security.TokenUserExtractor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TokenUserExtractorImpl implements TokenUserExtractor {

    JwtTokenProvider jwtTokenProvider;

    @Override
    public String getUserName(final String token) {
        return jwtTokenProvider.getUsernameFromToken(token);
    }

    @Override
    public String getUserId(final String token) {
        return jwtTokenProvider.getClaims(token, "userId");
    }
}
