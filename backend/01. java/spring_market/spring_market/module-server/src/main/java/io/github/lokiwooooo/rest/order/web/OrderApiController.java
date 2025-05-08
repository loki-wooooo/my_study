package io.github.lokiwooooo.rest.order.web;

import io.github.lokiwooooo.annotaion.PostAnnotation;
import io.github.lokiwooooo.rest.catogory.dto.CategoryRequestDto;
import io.github.lokiwooooo.rest.catogory.dto.CategoryResponseDto;
import io.github.lokiwooooo.rest.order.dto.OrderRequestDto;
import io.github.lokiwooooo.rest.order.dto.OrderResponseDto;
import io.github.lokiwooooo.rest.order.service.OrderService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "주문 관리")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequestMapping(path = "/rest/market/v1/order")
public class OrderApiController {

    OrderService orderService;


    @PostMapping
    @PostAnnotation
    @Operation(summary = "주문 등록", description = "새로운 주문을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "인가 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<OrderResponseDto> createOrder(
            @AuthenticationPrincipal final CustomUserDetails customUserDetails,
            @Valid @RequestBody final OrderRequestDto orderRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.create(customUserDetails, orderRequestDto));
    }

}
