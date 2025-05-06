package io.github.lokiwooooo.rest.user.dto;

import io.github.lokiwooooo.util.dto.ResponseCommonDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class UserResponseDto extends ResponseCommonDto {
    String id;
    String name;
    String password;
    String email;
}
