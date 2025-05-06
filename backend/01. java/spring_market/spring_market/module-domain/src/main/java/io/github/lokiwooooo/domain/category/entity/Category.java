package io.github.lokiwooooo.domain.category.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import io.github.lokiwooooo.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

// 상품 카테고리에 대한 정의
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CATEGORY")
@Table(name = "TB_CATEGORY")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Category extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CATEGORY_ID", nullable = false, updatable = false)
    @Comment("카테고리 Id")
    String id;

    @Column(name = "CATEGORY_NAME", nullable = false, length = 50)
    @Comment("카테고리 명")
    String name;

    @Column(name = "CATEGORY_LEVEL", nullable = false)
    @Comment("카테고리 계층 Level")
    Integer level;

    // 카테고리의 계층 Level에 따라 부모가 생김
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_PARENT_ID")
    Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();

}
