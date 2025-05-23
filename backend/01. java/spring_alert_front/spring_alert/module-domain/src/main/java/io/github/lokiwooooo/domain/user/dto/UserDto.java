package io.github.lokiwooooo.domain.user.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class UserDto extends CommonDto {
    Long id;
    String name;
    String email;
    String phoneNumber;
}
