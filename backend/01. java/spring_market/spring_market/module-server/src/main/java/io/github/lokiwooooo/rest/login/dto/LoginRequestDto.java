package io.github.lokiwooooo.rest.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class LoginRequestDto {
    @Schema(description = "사용자 이름", example = "username")
    String username;

    @Schema(description = "비밀번호", example = "password")
    String password;
}
