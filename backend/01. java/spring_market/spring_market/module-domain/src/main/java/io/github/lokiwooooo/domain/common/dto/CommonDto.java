package io.github.lokiwooooo.domain.common.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@MappedSuperclass
public class CommonDto {

    Boolean isUse;
    LocalDateTime createdOn;
    String createdUserId;
    String createdUserName;
    LocalDateTime lastEditedOn;
    String lastEditedUserId;
    String lastEditedUserName;

}
