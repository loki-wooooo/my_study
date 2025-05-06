package io.github.lokiwooooo.domain.order.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.orderitem.entity.OrderItem;
import io.github.lokiwooooo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 주문에 대한 정의
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDER")
@Table(name = "TB_ORDER")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Order extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ORDER_ID", nullable = false, updatable = false)
    @Comment("주문 Id")
    String id;

    @Column(name = "ORDER_DATE", nullable = false)
    @Comment("주문 일시")
    LocalDateTime date;

    @Column(name = "ORDER_DATE", nullable = false, length = 100)
    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @Column(name = "ORDER_TOTAL_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("주문 상품 가격")
    BigDecimal totalPrice;

    @Column(name = "ORDER_ADDRESS", nullable = false, precision = 12, scale = 2)
    @Comment("주문 주소")
    String address;

    // 양방향 X
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

}
