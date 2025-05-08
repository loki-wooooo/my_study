package io.github.lokiwooooo.security;

/**
 * 토큰에서 사용자 정보를 추출하기 위한 인터페이스
 */
public interface TokenUserExtractor {

    String getUserName(final String token);

    String getUserId(final String token);
}
