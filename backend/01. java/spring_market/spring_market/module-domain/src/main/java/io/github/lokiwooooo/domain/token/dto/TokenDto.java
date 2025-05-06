package io.github.lokiwooooo.domain.token.dto;

import io.github.lokiwooooo.domain.common.dto.CommonDto;


import io.github.lokiwooooo.domain.user.dto.UserDto;
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
public class TokenDto extends CommonDto implements Serializable {
    String tokenId;
    String accessToken;
    String refreshToken;
    UserDto userDto;
}
