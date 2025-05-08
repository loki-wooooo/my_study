package io.github.lokiwooooo.domain.category.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.product.dto.ProductDto;
import io.github.lokiwooooo.hierarchy.HierarchyNode;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class CategoryDto extends CommonDto implements Serializable, HierarchyNode<CategoryDto> {
    String id;
    String name;
    String level;
    String parentId;
    CategoryDto parentDto;
    List<CategoryDto> children;
    List<ProductDto> productDtoList;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getLevel() {
        return level;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public CategoryDto getParent() {
        return parentDto;
    }

    @Override
    public List<CategoryDto> getChildren() {
        return children;
    }

    @Override
    public void setParentId(String parentId){
        this.parentId = parentId;
    }

    @Override
    public void setChildren(List<CategoryDto> children) {
        this.children = children;
    }

}
