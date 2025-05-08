package io.github.lokiwooooo.rest.catogory.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class CategoryRequestDto {
    String id;
    String name;
    String level;
    String parentId;
    Boolean isUse;
}
