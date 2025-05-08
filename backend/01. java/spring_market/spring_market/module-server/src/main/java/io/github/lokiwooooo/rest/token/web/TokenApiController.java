package io.github.lokiwooooo.rest.token.web;

import io.github.lokiwooooo.annotaion.PostAnnotation;
import io.github.lokiwooooo.rest.token.dto.TokenRequestDto;
import io.github.lokiwooooo.rest.token.dto.TokenResponseDto;
import io.github.lokiwooooo.rest.token.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "토큰 관리")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequestMapping(path = "/rest/market/v1/token")
public class TokenApiController {

    TokenService tokenService;

    @PostMapping("/refresh")
    @PostAnnotation
    @Operation(summary = "토큰 재발급", description = "사용자의 토큰을 재발급 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<TokenResponseDto> refreshToken(
            @RequestHeader("kit_refresh_token") String refreshToken
    ) {
        //header에 있는 refresh token 취득
        TokenRequestDto tokenRequestDto = TokenRequestDto.builder().refreshToken(refreshToken).build();
        return ResponseEntity.ok(tokenService.refreshToken(tokenRequestDto));
    }

}
