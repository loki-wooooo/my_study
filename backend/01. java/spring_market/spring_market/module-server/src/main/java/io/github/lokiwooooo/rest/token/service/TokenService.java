package io.github.lokiwooooo.rest.token.service;

import io.github.lokiwooooo.domain.token.dto.TokenDto;
import io.github.lokiwooooo.domain.token.entity.Token;
import io.github.lokiwooooo.domain.token.repository.TokenMapper;
import io.github.lokiwooooo.domain.token.repository.TokenRepository;
import io.github.lokiwooooo.jwt.JwtTokenProvider;
import io.github.lokiwooooo.rest.token.dto.TokenRequestDto;
import io.github.lokiwooooo.rest.token.dto.TokenResponseDto;
import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import io.github.lokiwooooo.rest.user.dto.UserResponseDto;
import io.github.lokiwooooo.rest.user.service.UserService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import io.github.lokiwooooo.userdetail.CustomUserDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class TokenService {

    JwtTokenProvider jwtTokenProvider;
    CustomUserDetailsService customUserDetailsService;


    TokenService self;
    UserService userService;

    TokenRepository tokenRepository;
    TokenMapper tokenMapper;


    @Transactional
    public void insertToken(final TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = TokenDto.builder()
                .createdOn(LocalDateTime.now())
                .createdUserId(tokenRequestDto.getUserRequestDto().getId())
                .createdUserName(tokenRequestDto.getUserRequestDto().getName())
                .isUse(true)
                .lastEditedOn(LocalDateTime.now())
                .lastEditedUserId(tokenRequestDto.getUserRequestDto().getId())
                .lastEditedUserName(tokenRequestDto.getUserRequestDto().getName())
                .accessToken(tokenRequestDto.getAccessToken())
                .refreshToken(tokenRequestDto.getRefreshToken())
                .build();
        tokenRepository.save(tokenMapper.toEntity(tokenDto));
    }

    @Transactional
    public void deleteToken(final TokenRequestDto tokenRequestDto) {
        tokenRepository.deleteById(tokenRequestDto.getId());
    }

    @Transactional
    public void updateToken(final TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = TokenDto.builder()
                .id(tokenRequestDto.getId())
                .accessToken(tokenRequestDto.getAccessToken())
                .refreshToken(tokenRequestDto.getRefreshToken())
                .lastEditedOn(LocalDateTime.now())
                .lastEditedUserId(tokenRequestDto.getUserRequestDto().getId())
                .lastEditedUserName(tokenRequestDto.getUserRequestDto().getName())
                .build();
        tokenRepository.save(tokenMapper.toEntity(tokenDto));
    }

    public void flush() {
        tokenRepository.flush();
    }

    public TokenResponseDto findByRefreshToken(final TokenDto tokenDto) throws Exception {
        Token token = tokenRepository.findByRefreshToken(tokenDto.getRefreshToken()).orElse(null);
        TokenDto tokenDtoData = tokenMapper.toDto(token);
        return TokenResponseDto.builder()
                .accessToken(tokenDtoData.getAccessToken())
                .refreshToken(tokenDtoData.getRefreshToken())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public TokenResponseDto findByUserName(final TokenDto tokenDto) throws Exception {
        Token token = tokenRepository.findByUserName(tokenDto.getUserDto().getName()).orElse(null);
        TokenDto tokenDtoData = tokenMapper.toDto(token);
        return TokenResponseDto.builder()
                .id(tokenDtoData.getId())
                .accessToken(tokenDtoData.getAccessToken())
                .refreshToken(tokenDtoData.getRefreshToken())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public TokenResponseDto findByIsUseAndUserName(final TokenDto tokenDto) throws Exception {
        Token token = tokenRepository.findByIsUseAndUserName(tokenDto.getIsUse(), tokenDto.getUserDto().getName()).orElse(null);
        TokenDto tokenDtoData = tokenMapper.toDto(token);
        return TokenResponseDto.builder()
                .id(tokenDtoData.getId())
                .accessToken(tokenDtoData.getAccessToken())
                .refreshToken(tokenDtoData.getRefreshToken())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    // 토큰 재발급
    public TokenResponseDto refreshToken(final TokenRequestDto tokenRequestDto) throws Exception {

        // Step1. RefreshToken 유효성 검증
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            return TokenResponseDto.builder().httpStatus(HttpStatus.FORBIDDEN).build();
        }

        // Step2. Refresh Token에서 username 추출
        String username = jwtTokenProvider.getUsernameFromToken(tokenRequestDto.getRefreshToken());

        // Step.3 DB에 있는 refreshToken equals 현제 받은 refresh-token
        TokenResponseDto tokenResponseDto = findByRefreshToken(TokenDto.builder().refreshToken(tokenRequestDto.getRefreshToken()).build());
        if (tokenResponseDto == null) {
            return TokenResponseDto.builder().httpStatus(HttpStatus.NOT_FOUND).build();
        }
        if (!tokenResponseDto.getRefreshToken().equals(tokenRequestDto.getRefreshToken())) {
            return TokenResponseDto.builder().httpStatus(HttpStatus.BAD_REQUEST).build();
        }

        // Step4. 사용자 정보 조회 (DB(O) or 캐시) -> 사용자가 많아진다면 Redis 고려 필요함
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        String newAccessToken = jwtTokenProvider.generateAccessToken(userDetails);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

        UserRequestDto userRequestDto = UserRequestDto.builder().name(username).build();
        UserResponseDto userResponseDto = userService.findByName(userRequestDto);

        if (userResponseDto == null) {
            return TokenResponseDto.builder().httpStatus(HttpStatus.BAD_REQUEST).build();
        }

        // Step5. Token 정보 삭제 후 재등록
        TokenRequestDto deleteTokenRequestDto = TokenRequestDto.builder().id(tokenResponseDto.getId()).accessToken(newAccessToken).refreshToken(newRefreshToken).build();
        self.deleteToken(deleteTokenRequestDto);

        TokenRequestDto insertTokenRequestDto = TokenRequestDto.builder()
                .userRequestDto(UserRequestDto.builder().id(userResponseDto.getId()).name(userResponseDto.getName()).email(userResponseDto.getEmail()).build())
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
        self.insertToken(insertTokenRequestDto);

        return TokenResponseDto.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
    }


}
