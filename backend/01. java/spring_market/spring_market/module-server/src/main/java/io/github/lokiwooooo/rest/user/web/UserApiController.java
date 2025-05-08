package io.github.lokiwooooo.rest.user.web;

import io.github.lokiwooooo.annotaion.GetAnnotation;
import io.github.lokiwooooo.rest.user.dto.UserListResponseDto;
import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import io.github.lokiwooooo.rest.user.dto.UserResponseDto;
import io.github.lokiwooooo.rest.user.service.UserService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "사용자 관리")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequestMapping(path = "/rest/market/v1/users")
public class UserApiController {

    UserService userService;

    @GetMapping
    @GetAnnotation
    @Operation(summary = "사용자 목록 불러오기", description = "사용자 목록을 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<UserListResponseDto> getUserList(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
            , final UserRequestDto userRequestDto) {
        UserListResponseDto userListResponseDto = userService.findAll(customUserDetails, userRequestDto);
        return new ResponseEntity<>(userListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @GetAnnotation
    @Operation(summary = "사용자 상세 목록 불러오기", description = "사용자 상세 목록을 호출합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "404", description = "페이지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<UserResponseDto> getUserById(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails
            , @PathVariable final String id
    ) {
        return ResponseEntity.ok(userService.findById(customUserDetails, id));
    }

}
