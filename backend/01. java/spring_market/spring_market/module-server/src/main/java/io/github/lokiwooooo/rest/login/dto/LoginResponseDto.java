package io.github.lokiwooooo.rest.login.dto;

import io.github.lokiwooooo.util.dto.ResponseCommonDto;
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
public class LoginResponseDto extends ResponseCommonDto {

    @Schema(description = "Access 토큰", example = "")
    String accessToken;

    @Schema(description = "Refresh 토큰", example = "")
    String refreshToken;
}
