package io.github.lokiwooooo.domain.token.repository;


import io.github.lokiwooooo.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByRefreshToken(final String refreshToken);

    Optional<Token> findByUserName(final String userName);

    Optional<Token> findByIsUseAndUserName(final Boolean isUse, final String userName);

    Optional<Token> findByIsUseAndAccessToken(final Boolean isUse, final String accessToken);
}
