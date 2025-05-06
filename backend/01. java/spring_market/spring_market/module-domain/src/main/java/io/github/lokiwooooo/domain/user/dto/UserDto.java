package io.github.lokiwooooo.domain.user.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class UserDto extends CommonDto implements Serializable {
    String id;
    String name;
    String password;
    String email;
}
