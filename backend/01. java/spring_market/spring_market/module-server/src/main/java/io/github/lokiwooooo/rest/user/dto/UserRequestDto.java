package io.github.lokiwooooo.rest.user.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class UserRequestDto {
    String id;
    String name;
    String password;
    String email;
}
