package io.github.lokiwooooo.rest.login.web;

import io.github.lokiwooooo.annotaion.GetAnnotation;
import io.github.lokiwooooo.annotaion.PostAnnotation;
import io.github.lokiwooooo.rest.login.dto.LoginRequestDto;
import io.github.lokiwooooo.rest.login.dto.LoginResponseDto;
import io.github.lokiwooooo.rest.login.service.LoginService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "로그인")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequestMapping(path = "/rest/market/v1/auth")
public class LoginApiController {

    LoginService loginService;

    @PostMapping("/login")
    @PostAnnotation
    @Operation(summary = "로그인", description = "사용자 로그인을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody final LoginRequestDto loginRequestDto
    ) {
        return ResponseEntity.ok(loginService.login(loginRequestDto));
    }

    @GetMapping("/logout")
    @GetAnnotation
    @Operation(summary = "로그아웃", description = "사용자 로그아웃을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<LoginResponseDto> logout(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
    ) throws Exception {
        return ResponseEntity.ok(loginService.logout(customUserDetails));
    }

}
