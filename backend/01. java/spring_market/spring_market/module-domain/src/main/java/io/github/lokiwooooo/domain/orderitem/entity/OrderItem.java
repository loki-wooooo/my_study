package io.github.lokiwooooo.domain.orderitem.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.order.entity.Order;
import io.github.lokiwooooo.domain.orderitemadditionalproduct.entity.OrderItemAdditionalProduct;
import io.github.lokiwooooo.domain.orderitemoption.entity.OrderItemOption;
import io.github.lokiwooooo.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER_ITEM")
@Table(name = "TB_ORDER_ITEM")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class OrderItem extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ORDER_ITEM_ID", nullable = false, updatable = false)
    @Comment("주문 상품 Id")
    String id;

    // 주문 시점 복제 필드 (상품명, 옵션 요약)
    @Column(name = "ORDER_ITEM_PRODUCT_NAME", nullable = false, length = 50)
    @Comment("주문 상품 명")
    String name;

    // 주문 시점 복제 필드 (상품명, 옵션 요약)
    @Column(name = "ORDER_ITEM_PRODUCT_CONTENT", nullable = true, length = 2000)
    @Comment("주문 상품 내용")
    String content;

    @Column(name = "ORDER_ITEM_PRODUCT_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("주문 상품 가격")
    BigDecimal price;

    @Column(name = "ORDER_ITEM_PRODUCT_QUANTITY", nullable = false)
    @Comment("주문 상품 수량")
    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    Order order = new Order();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    Product product = new Product();

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
    List<OrderItemOption> orderItemOptionList = new ArrayList<>();

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
    List<OrderItemAdditionalProduct> orderItemAdditionalProductList = new ArrayList<>();
}
