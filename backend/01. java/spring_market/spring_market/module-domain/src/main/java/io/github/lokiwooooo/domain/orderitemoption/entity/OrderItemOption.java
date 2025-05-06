package io.github.lokiwooooo.domain.orderitemoption.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;


// 상품 옵션의 데이터가 저장
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER_ITEM_OPTION")
@Table(name = "TB_ORDER_ITEM_OPTION")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class OrderItemOption extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ORDER_ITEM_OPTION_ID", nullable = false, updatable = false)
    @Comment("주문 상품 Id")
    String id;

    @Column(name = "ORDER_ITEM_OPTION_PRODUCT_OPTION_NAME", nullable = false, length = 200)
    @Comment("옵션 상품 명")
    String name;  // 예: "색상"

    @Column(name = "ORDER_ITEM_OPTION_PRODUCT_OPTION_VALUE", nullable = false, length = 200)
    @Comment("옵션 상품 값")
    String value;      // 예: "빨강"

    @Column(name = "ORDER_ITEM_OPTION_PRODUCT_OPTION_ADDITIONAL_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("옵션 상품 추가 가격")
    BigDecimal additionalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ITEM_ID")
    OrderItem orderItem = new OrderItem();

}
