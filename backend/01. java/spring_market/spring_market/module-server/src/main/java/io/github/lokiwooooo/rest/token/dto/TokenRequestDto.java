package io.github.lokiwooooo.rest.token.dto;


import io.github.lokiwooooo.rest.user.dto.UserRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class TokenRequestDto {

    @Schema(description = "토큰 ID", example = "46839691-799c-4d95-8e0f-46b951810d7e")
    String id;

    @Schema(description = "Access 토큰", example = "")
    String accessToken;

    @Schema(description = "Refresh 토큰", example = "")
    String refreshToken;

    @Schema(description = "사용 여부", example = "true")
    Boolean isUse;

    UserRequestDto userRequestDto;
}
