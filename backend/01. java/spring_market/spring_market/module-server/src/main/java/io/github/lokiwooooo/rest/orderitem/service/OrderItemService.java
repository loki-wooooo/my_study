package io.github.lokiwooooo.rest.orderitem.service;

import io.github.lokiwooooo.domain.orderitem.dto.OrderItemDto;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import io.github.lokiwooooo.domain.orderitem.repository.OrderItemMapper;
import io.github.lokiwooooo.domain.orderitem.repository.OrderItemRepository;
import io.github.lokiwooooo.rest.order.dto.OrderResponseDto;
import io.github.lokiwooooo.userdetail.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
@Slf4j
public class OrderItemService {

    OrderItemRepository orderItemRepository;
    OrderItemMapper orderItemMapper;

    @Transactional
    public OrderResponseDto createByDto(final CustomUserDetails customUserDetails, final OrderItemDto orderItemDto) {
        OrderItem saveOrderItem = orderItemRepository.save(orderItemMapper.toEntity(orderItemDto));
        return OrderResponseDto.builder().id(saveOrderItem.getId().toString()).build();
    }
}
