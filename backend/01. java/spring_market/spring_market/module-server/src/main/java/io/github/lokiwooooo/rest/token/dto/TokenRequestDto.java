package io.github.lokiwooooo.rest.token.dto;


import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class TokenRequestDto {
    String id;
    String accessToken;
    String refreshToken;
    UserRequestDto userRequestDto;
}
