package io.github.lokiwooooo.domain.additionalproduct.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

// 추가 상품에 대한 정의
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ADDITIONAL_PRODUCT")
@Table(name = "TB_ADDITIONAL_PRODUCT")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class AdditionalProduct extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ADDITIONAL_PRODUCT_ID", nullable = false, updatable = false)
    @Comment("추가 상품 Id")
    String id;

    @Column(name = "ADDITIONAL_PRODUCT_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("추가 상품 가격")
    BigDecimal price;

    @Column(name = "ADDITIONAL_PRODUCT_STOCK", nullable = false)
    @Comment("추가 상품 수량")
    Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    Product product = new Product();
}
