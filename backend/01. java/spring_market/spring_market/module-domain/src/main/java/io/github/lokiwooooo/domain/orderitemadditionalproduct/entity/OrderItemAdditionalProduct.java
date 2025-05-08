package io.github.lokiwooooo.domain.orderitemadditionalproduct.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

// 추가 상품 옵션의 데이터가 저장
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER_ITEM_ADDITIONAL_PRODUCT")
@Table(name = "TB_ORDER_ITEM_ADDITIONAL_PRODUCT")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class OrderItemAdditionalProduct extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ORDER_ITEM_ADDITIONAL_PRODUCT_ID", nullable = false, updatable = false)
    @Comment("주문 추가 상품 Id")
    String id;

    @Column(name = "ORDER_ITEM_ADDITIONAL_PRODUCT_NAME", nullable = false)
    @Comment("주문 추가 상품 명")
    String name;  // 예: "보증연장"

    @Column(name = "ORDER_ITEM_OPTION_PRODUCT_OPTION_ADDITIONAL_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("주문 추가 상품 가격")
    BigDecimal price;

    @Column(name = "ORDER_ITEM_OPTION_PRODUCT_OPTION_ADDITIONAL_QUANTITY", nullable = false)
    @Comment("주문 추가 상품 수량")
    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ITEM_ID")
    OrderItem orderItem;
}
