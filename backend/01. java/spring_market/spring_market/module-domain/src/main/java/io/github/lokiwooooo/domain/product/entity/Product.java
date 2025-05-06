package io.github.lokiwooooo.domain.product.entity;

import io.github.lokiwooooo.domain.additionalproduct.entity.AdditionalProduct;
import io.github.lokiwooooo.domain.category.entity.Category;
import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.productoptiongroup.entity.ProductOptionGroup;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 상품에 대한 정의
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCT")
@Table(name = "TB_PRODUCT")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Product extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PRODUCT_ID", nullable = false, updatable = false)
    @Comment("상품 Id")
    String id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 50)
    @Comment("상품 명")
    String name;

    @Column(name = "PRODUCT_CONTENT", nullable = true, length = 2000)
    @Comment("상품 내용")
    String content;

    /**
     * precision - 전체 자릿수
     * scale - 소수점 이하 2자리
     * */
    @Column(name = "PRODUCT_PRICE", nullable = false, precision = 12, scale = 2)
    @Comment("상품 가격")
    BigDecimal price;

    @Column(name = "PRODUCT_STOCK", nullable = false)
    @Comment("상품 수량")
    Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    Category category = new Category();

    //상품 옵션과 1:N
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ProductOptionGroup> productOptionGroup = new ArrayList<>();

    //추가 상품과 1:N
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<AdditionalProduct> additionalProducts = new ArrayList<>();

}
