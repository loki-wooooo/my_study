package io.github.lokiwooooo.domain.productoptiongroup.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.product.entity.Product;
import io.github.lokiwooooo.domain.productoption.entity.ProductOption;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

// 상품 옵션의 그룹을 정의함(색상, 사이즈 등등..)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCT_OPTION_GROUP")
@Table(name = "TB_PRODUCT_OPTION_GROUP")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class ProductOptionGroup extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PRODUCT_OPTION_GROUP_ID", nullable = false, updatable = false)
    @Comment("상품 옵션 그룹 Id")
    String id;

    @Column(name = "PRODUCT_OPTION_GROUP_NAME", nullable = false, length = 50)
    @Comment("상품 옵션 명")
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    Product product;

    @OneToMany(mappedBy = "productOptionGroup", cascade = CascadeType.ALL)
    List<ProductOption> productOptionList = new ArrayList<>();


}
