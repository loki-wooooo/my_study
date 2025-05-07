package io.github.lokiwooooo.rest.login.service;

import io.github.lokiwooooo.jwt.JwtTokenProvider;
import io.github.lokiwooooo.jwt.JwtTokenResponse;
import io.github.lokiwooooo.rest.login.dto.LoginRequestDto;
import io.github.lokiwooooo.rest.login.dto.LoginResponseDto;
import io.github.lokiwooooo.rest.token.dto.TokenRequestDto;
import io.github.lokiwooooo.rest.token.dto.TokenResponseDto;
import io.github.lokiwooooo.rest.token.service.TokenService;
import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import io.github.lokiwooooo.rest.user.dto.UserResponseDto;
import io.github.lokiwooooo.rest.user.service.UserService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class LoginService {

    JwtTokenProvider jwtTokenProvider;
    AuthenticationManager authenticationManager;

    UserService userService;
    TokenService tokenService;

    @Transactional
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {

        // Step1. 로그인 인증
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),   // 필드명 확인!
                loginRequestDto.getPassword()));

        // Step2. 토큰 정보 반환
        JwtTokenResponse jwtTokenResponse = jwtTokenProvider.generateToken(authentication);
        String accessToken = jwtTokenResponse.getAccessToken();
        String refreshToken = jwtTokenResponse.getRefreshToken();

        // Step3. 토큰 정보 저장
        UserRequestDto userRequestDto = UserRequestDto.builder().name(loginRequestDto.getUsername()).build();
        UserResponseDto userResponseDto = userService.findByName(userRequestDto);
        UserRequestDto userRequestData = UserRequestDto.builder()
                .id(userResponseDto.getId())
                .name(userResponseDto.getName())
                .email(userResponseDto.getEmail())
                .build();

        // 기존 토큰이 있다면 논리 삭제(만료처리를 위해)

        /**
         * 기존 토큰을 갖고와서 해당 토큰이 만료되지 않았다면, 기존 토큰 리턴
         * 기존 토큰을 갖고와서 해당 토큰이 만료되었다면, 이전토큰 사용유무 "false" 업데이트 이후 신규 토큰 생성
         * */
        TokenRequestDto tokenRequestDto = TokenRequestDto.builder().isUse(true).userRequestDto(userRequestData).build();
        TokenResponseDto tokenResponseDto = tokenService.findByUserName(tokenRequestDto);
        if (tokenResponseDto != null) {

            String userAccessToken = tokenResponseDto.getAccessToken();
            String userRefreshToken = tokenResponseDto.getRefreshToken();
            if (jwtTokenProvider.validateToken(userAccessToken) && jwtTokenProvider.validateToken(userRefreshToken) && tokenResponseDto.getIsUse()) {
                return LoginResponseDto.builder().accessToken(userAccessToken).refreshToken(userRefreshToken).httpStatus(HttpStatus.OK).build();
            } else {

                // 이전 토큰은 "미사용"으로 변경
                TokenRequestDto updateTokenRequestDto = TokenRequestDto.builder()
                        .id(tokenResponseDto.getId())
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .isUse(false)
                        .build();
                tokenService.updateToken(updateTokenRequestDto);
                tokenService.flush();

                // 신규 생성할 토큰을 "사용"으로 등록
                TokenRequestDto insertTokenRequestDto = TokenRequestDto.builder()
                        .userRequestDto(userRequestData)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                tokenService.insertToken(insertTokenRequestDto);
            }
        } else {
            TokenRequestDto insertTokenRequestDto = TokenRequestDto.builder()
                    .userRequestDto(userRequestData)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            tokenService.insertToken(insertTokenRequestDto);
        }
        return LoginResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).httpStatus(HttpStatus.OK).build();
    }

    @Transactional
    public LoginResponseDto logout(
            final CustomUserDetails customUserDetails
    ) throws Exception {
        // JWT 토큰 삭제 (DB)
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(customUserDetails.getUsername())
                .build();
        UserResponseDto userResponseDto = userService.findByName(userRequestDto);
        UserRequestDto userRequestData = UserRequestDto.builder()
                .id(userResponseDto.getId())
                .name(userResponseDto.getName())
                .email(userResponseDto.getEmail())
                .build();

        TokenRequestDto tokenRequestDto = TokenRequestDto.builder().userRequestDto(userRequestData).build();

        TokenResponseDto tokenResponseDto = tokenService.findByUserName(tokenRequestDto);
        if (tokenResponseDto != null) {
            TokenRequestDto tokenRequestData = TokenRequestDto.builder().isUse(false).userRequestDto(userRequestData).build();
            tokenService.updateToken(tokenRequestData);
        }
        return LoginResponseDto.builder().httpStatus(HttpStatus.OK).build();
    }


}
