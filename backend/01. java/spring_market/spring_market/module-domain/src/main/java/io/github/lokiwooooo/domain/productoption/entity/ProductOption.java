package io.github.lokiwooooo.domain.productoption.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.productoptiongroup.entity.ProductOptionGroup;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

// 상품 옵션 정보들(색상에 대한 내용, 사이즈에 대한 내용 등등..)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCT_OPTION")
@Table(name = "TB_PRODUCT_OPTION")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ProductOption extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PRODUCT_OPTION_ID", nullable = false, updatable = false)
    @Comment("옵션 상품 Id")
    String id;

    @Column(name = "PRODUCT_OPTION_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("옵션 상품 추가 가격")
    BigDecimal price;

    @Column(name = "PRODUCT_OPTION_VALUE", nullable = false, length = 200)
    @Comment("옵션 상품 값")
    String value;

    @Column(name = "PRODUCT_OPTION_STOCK", nullable = false)
    @Comment("옵션 상품 수량")
    Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_OPTION_GROUP_ID")
    ProductOptionGroup productOptionGroup = new ProductOptionGroup();

}
