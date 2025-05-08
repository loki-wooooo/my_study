package io.github.lokiwooooo.rest.order.service;

import io.github.lokiwooooo.domain.order.dto.OrderDto;
import io.github.lokiwooooo.domain.order.entity.Order;
import io.github.lokiwooooo.domain.order.entity.OrderStatus;
import io.github.lokiwooooo.domain.order.repository.OrderMapper;
import io.github.lokiwooooo.domain.order.repository.OrderRepository;
import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.orderitem.repository.OrderItemRepository;
import io.github.lokiwooooo.domain.orderitemadditionalproduct.dto.OrderItemAdditionalProductDto;
import io.github.lokiwooooo.domain.orderitemoption.dto.OrderItemOptionDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.domain.user.dto.UserDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductRequestDto;
import io.github.lokiwooooo.rest.additionalproduct.dto.AdditionalProductResponseDto;
import io.github.lokiwooooo.rest.additionalproduct.service.AdditionalProductService;
import io.github.lokiwooooo.rest.order.dto.OrderRequestDto;
import io.github.lokiwooooo.rest.order.dto.OrderResponseDto;
import io.github.lokiwooooo.rest.orderitem.dto.OrderItemRequestDto;
import io.github.lokiwooooo.rest.orderitem.service.OrderItemService;
import io.github.lokiwooooo.rest.orderitemadditionalproduct.dto.OrderItemAdditionalProductRequestDto;
import io.github.lokiwooooo.rest.orderitemoption.dto.OrderItemOptionRequestDto;
import io.github.lokiwooooo.rest.product.dto.ProductRequestDto;
import io.github.lokiwooooo.rest.product.dto.ProductResponseDto;
import io.github.lokiwooooo.rest.product.service.ProductService;
import io.github.lokiwooooo.rest.productoption.dto.ProductOptionRequestDto;
import io.github.lokiwooooo.rest.productoption.dto.ProductOptionResponseDto;
import io.github.lokiwooooo.rest.productoption.service.ProductOptionService;
import io.github.lokiwooooo.rest.user.dto.UserResponseDto;
import io.github.lokiwooooo.rest.user.service.UserService;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    UserService userService;
    ProductService productService;
    ProductOptionService productOptionService;
    AdditionalProductService additionalProductService;
    OrderItemService orderItemService;


    @Transactional
    public OrderResponseDto create(
            final CustomUserDetails customUserDetails
            , final OrderRequestDto orderRequestDto
    ) {
        // 유저 정보 확인
        UserResponseDto userResponseDto = userService.findById(customUserDetails, customUserDetails.getUserId());
        UserDto userDto = UserDto.builder()
                .id(userResponseDto.getId())
                .email(userResponseDto.getEmail())
                .name(userResponseDto.getName())
                .build();

        // 주문 기본 정보 생성
        OrderDto orderDto = OrderDto.builder()
                .date(LocalDateTime.now())
                .address(orderRequestDto.getAddress())
                .totalPrice(BigDecimal.ZERO)
                .status(OrderStatus.CREATED)
                .isUse(true)
                .userDto(userDto)
                .createdOn(LocalDateTime.now())
                .createdUserId(userDto.getId())
                .createdUserName(userDto.getName())
                .lastEditedOn(LocalDateTime.now())
                .lastEditedUserId(userDto.getId())
                .lastEditedUserName(userDto.getName())
                .build();

        //초기 order 저장
        Order saveOrder = orderRepository.save(orderMapper.toEntity(orderDto));
        orderDto.setId(saveOrder.getId());

        // 주문 상품 목록 처리
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        // 사전 재고 확인 - 주문 처리 전에 모든 상품의 재고를 먼저 확인
        if (orderRequestDto.getOrderItemRequestDtoList() != null && !orderRequestDto.getOrderItemRequestDtoList().isEmpty()) {
            for (OrderItemRequestDto orderItemRequestDto : orderRequestDto.getOrderItemRequestDtoList()) {
                // 상품 정보 조회
                ProductResponseDto productResponseDto = productService.findById(customUserDetails, orderItemRequestDto.getProductId());

                // 단일 상품 재고 확인
                if (productResponseDto.getStock() < orderItemRequestDto.getQuantity()) {
                    throw new IllegalArgumentException("상품 '" + productResponseDto.getName()
                            + "'의 재고가 부족합니다. 현재 재고: " + productResponseDto.getStock()
                            + ", 요청 수량: " + orderItemRequestDto.getQuantity());
                }

                // 상품 옵션 재고 확인
                if (orderItemRequestDto.getOrderItemOptionRequestDtoList() != null &&
                        !orderItemRequestDto.getOrderItemOptionRequestDtoList().isEmpty()) {

                    for (OrderItemOptionRequestDto orderItemOptionRequestDto : orderItemRequestDto.getOrderItemOptionRequestDtoList()) {
                        ProductOptionResponseDto productOptionResponseDto = productResponseDto.getProductOptionGroupResponseDtoList().stream()
                                .flatMap(productOptionGroupResponseDto -> productOptionGroupResponseDto.getProductOptionResponseDtoList().stream())
                                .filter(pord -> pord.getId().equals(orderItemOptionRequestDto.getId()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "존재하지 않은 상품 옵션입니다: " + orderItemOptionRequestDto.getId()));

                        // 옵션 재고 확인
                        if (productOptionResponseDto.getStock() < orderItemRequestDto.getQuantity()) {
                            throw new IllegalArgumentException("상품 옵션 '" + productOptionResponseDto.getValue()
                                    + "'의 재고가 부족합니다. 현재 재고: " + productOptionResponseDto.getStock()
                                    + ", 요청 수량: " + orderItemRequestDto.getQuantity());
                        }
                    }
                }

                // 추가 상품 재고 확인
                if (orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList() != null &&
                        !orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList().isEmpty()) {

                    for (OrderItemAdditionalProductRequestDto additionalProductRequestDto : orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList()) {
                        AdditionalProductResponseDto additionalProductResponseDto = productResponseDto.getAdditionalProductResponseDtoList().stream()
                                .filter(ap -> ap.getId().equals(additionalProductRequestDto.getId()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "존재하지 않은 추가 상품입니다: " + additionalProductRequestDto.getId()));

                        // 추가 상품의 재고 확인
                        if (additionalProductResponseDto.getStock() < additionalProductRequestDto.getQuantity()) {
                            throw new IllegalArgumentException("추가 상품 '" + additionalProductResponseDto.getName()
                                    + "'의 재고가 부족합니다. 현재 재고: " + additionalProductResponseDto.getStock()
                                    + ", 요청 수량: " + additionalProductRequestDto.getQuantity());
                        }
                    }
                }
            }
        }

        // 주문 아이템 처리
        if (orderRequestDto.getOrderItemRequestDtoList() != null && !orderRequestDto.getOrderItemRequestDtoList().isEmpty()) {

            for (OrderItemRequestDto orderItemRequestDto : orderRequestDto.getOrderItemRequestDtoList()) {

                // 상품 정보 조회
                ProductResponseDto productResponseDto = productService.findById(customUserDetails, orderItemRequestDto.getProductId());
                ProductDto productDto = ProductDto.builder()
                        .id(productResponseDto.getId())
                        .name(productResponseDto.getName())
                        .content(productResponseDto.getContent())
                        .price(productResponseDto.getPrice())
                        .build();

                // 주문 아이템 DTO 생성 & 기본 상품만 주문 (추가 처리 필요 없음)
                OrderItemDto orderItemDto = OrderItemDto.builder()
                        .name(productDto.getName())
                        .content(productDto.getContent())
                        .price(productDto.getPrice())
                        .quantity(orderItemRequestDto.getQuantity())
//                        .productDto(productDto)
                        .productId(productDto.getId())
                        .orderId(orderDto.getId())
                        .isUse(true)
                        .createdOn(LocalDateTime.now())
                        .createdUserId(userDto.getId())
                        .createdUserName(userDto.getName())
                        .lastEditedOn(LocalDateTime.now())
                        .lastEditedUserId(userDto.getId())
                        .lastEditedUserName(userDto.getName())
                        .build();

                OrderResponseDto orderResponseDto = orderItemService.createByDto(customUserDetails, orderItemDto);
                orderItemDto.setId(orderResponseDto.getId());

                // 기본 상품 가격 계산 (상품가격 * 상품 갯수)
                BigDecimal itemTotalPrice = productDto.getPrice().multiply(BigDecimal.valueOf(orderItemRequestDto.getQuantity()));


                // 상품 옵션 처리(상품이 옵션으로 있는경우)
                List<OrderItemOptionDto> orderItemOptionDtoList = new ArrayList<>();

                if (orderItemRequestDto.getOrderItemOptionRequestDtoList() != null &&
                        !orderItemRequestDto.getOrderItemOptionRequestDtoList().isEmpty()) {

                    for (OrderItemOptionRequestDto orderItemOptionRequestDto : orderItemRequestDto.getOrderItemOptionRequestDtoList()) {

                        // 옵션 정보 검증 및 조회
                        // 내가 선택한 옵션이 실제 DB에 있는지 확인
                        ProductOptionResponseDto productOptionResponseDto = productResponseDto.getProductOptionGroupResponseDtoList().stream()
                                .flatMap(productOptionGroupResponseDto -> productOptionGroupResponseDto.getProductOptionResponseDtoList().stream())
                                .filter(pord -> pord.getId().equals(orderItemOptionRequestDto.getId()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "존재하지 않은 상품 옵션입니다: " + orderItemOptionRequestDto.getId()));

                        // 옵션 DTO 생성
                        OrderItemOptionDto orderItemOptionDto = OrderItemOptionDto.builder()
                                .name(productDto.getName()) // 상품명
                                .value(productOptionResponseDto.getValue()) // 옵션 명
                                .additionalPrice(productOptionResponseDto.getPrice()) //옵션 추가값
                                .orderItemDto(orderItemDto)
                                .orderItemId(orderItemDto.getId())
                                .isUse(true)
                                .createdOn(LocalDateTime.now())
                                .createdUserId(userDto.getId())
                                .createdUserName(userDto.getName())
                                .lastEditedOn(LocalDateTime.now())
                                .lastEditedUserId(userDto.getId())
                                .lastEditedUserName(userDto.getName())
                                .build();

                        orderItemOptionDtoList.add(orderItemOptionDto);

                        // 옵션 가격 추가 (옵션 추가 가격 * 수량)
                        itemTotalPrice = itemTotalPrice.add(
                                orderItemOptionDto.getAdditionalPrice().multiply(BigDecimal.valueOf(orderItemRequestDto.getQuantity()))
                        );
                    }
                }

                // 추가 상품 처리 (상품에 추가상품이 있는경우)
                List<OrderItemAdditionalProductDto> additionalProductDtoList = new ArrayList<>();

                if (orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList() != null &&
                        !orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList().isEmpty()) {

                    for (OrderItemAdditionalProductRequestDto additionalProductRequestDto : orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList()) {
                        // 추가 상품 정보 검증 및 조회
                        AdditionalProductResponseDto additionalProductResponseDto = productResponseDto.getAdditionalProductResponseDtoList().stream()
                                .filter(ap -> ap.getId().equals(additionalProductRequestDto.getId()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "존재하지 않은 추가 상품입니다: " + additionalProductRequestDto.getId()));

                        // 추가 상품 DTO 생성
                        OrderItemAdditionalProductDto additionalProductDto = OrderItemAdditionalProductDto.builder()
                                .name(additionalProductResponseDto.getName())
                                .price(additionalProductRequestDto.getPrice())
                                .quantity(additionalProductRequestDto.getQuantity())
                                .orderItemDto(orderItemDto)
                                .orderItemId(orderItemDto.getId())
                                .isUse(true)
                                .createdOn(LocalDateTime.now())
                                .createdUserId(userDto.getId())
                                .createdUserName(userDto.getName())
                                .lastEditedOn(LocalDateTime.now())
                                .lastEditedUserId(userDto.getId())
                                .lastEditedUserName(userDto.getName())
                                .build();

                        additionalProductDtoList.add(additionalProductDto);

                        // 추가 상품 가격 추가 (수량 고려)
                        BigDecimal additionalPrice = additionalProductDto.getPrice()
                                .multiply(BigDecimal.valueOf(additionalProductRequestDto.getQuantity()));
                        itemTotalPrice = itemTotalPrice.add(additionalPrice);
                    }
                }

                // 주문 아이템에 옵션과 추가 상품 설정
                orderItemDto.setOrderItemOptionDtoList(orderItemOptionDtoList);
                orderItemDto.setOrderItemAdditionalProductDtoList(additionalProductDtoList);

                // 주문 아이템의 총 가격 설정
                orderItemDto.setPrice(itemTotalPrice);

                // 주문 아이템 목록에 추가
                orderItemDtoList.add(orderItemDto);

                // 전체 주문 가격에 합산
                totalPrice = totalPrice.add(itemTotalPrice);

                // 재고 차감 처리
                ProductRequestDto updateProductRequestDto = ProductRequestDto.builder()
                        .id(productResponseDto.getId())
                        .stock(productResponseDto.getStock() - orderItemRequestDto.getQuantity())
                        .build();
                productService.updateStock(customUserDetails, updateProductRequestDto);

                // 옵션 재고 차감 처리
                if (orderItemRequestDto.getOrderItemOptionRequestDtoList() != null &&
                        !orderItemRequestDto.getOrderItemOptionRequestDtoList().isEmpty()) {

                    for (OrderItemOptionRequestDto orderItemOptionRequestDto : orderItemRequestDto.getOrderItemOptionRequestDtoList()) {
                        ProductOptionResponseDto productOptionResponseDto = productResponseDto.getProductOptionGroupResponseDtoList().stream()
                                .flatMap(productOptionGroupResponseDto -> productOptionGroupResponseDto.getProductOptionResponseDtoList().stream())
                                .filter(pord -> pord.getId().equals(orderItemOptionRequestDto.getId()))
                                .findFirst()
                                .orElseThrow();

                        // 옵션 재고 차감
                        ProductOptionRequestDto productOptionRequestDto = ProductOptionRequestDto.builder()
                                .id(productOptionResponseDto.getId())
                                .stock(productOptionResponseDto.getStock() - orderItemRequestDto.getQuantity())
                                .build();
                        productOptionService.updateProductOptionStock(customUserDetails, productOptionRequestDto);
                    }
                }

                // 추가 상품 재고 차감
                if (orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList() != null &&
                        !orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList().isEmpty()) {

                    for (OrderItemAdditionalProductRequestDto orderItemAdditionalProductRequestDto : orderItemRequestDto.getOrderItemAdditionalProductRequestDtoList()) {
                        AdditionalProductResponseDto additionalProductResponseDto = productResponseDto.getAdditionalProductResponseDtoList().stream()
                                .filter(ap -> ap.getId().equals(orderItemAdditionalProductRequestDto.getId()))
                                .findFirst()
                                .orElseThrow();

                        // 추가 상품 재고 차감
                        AdditionalProductRequestDto additionalProductRequestDto = AdditionalProductRequestDto.builder()
                                .id(additionalProductResponseDto.getId())
                                .stock(additionalProductResponseDto.getStock() - orderItemAdditionalProductRequestDto.getQuantity())
                                .build();
                        additionalProductService.updateAdditionalProductStock(customUserDetails, additionalProductRequestDto);
                    }
                }

            }
        }

        // 주문 DTO에 아이템 목록과 총 가격 설정
        orderDto.setOrderItemDtoList(orderItemDtoList);
        orderDto.setTotalPrice(totalPrice);
        orderRepository.save(orderMapper.toEntity(orderDto));

        return OrderResponseDto.builder()
                .id(orderDto.getId())
                .date(orderDto.getDate())
                .status(orderDto.getStatus())
                .totalPrice(orderDto.getTotalPrice())
                .address(orderDto.getAddress())
                .build();
    }

}
