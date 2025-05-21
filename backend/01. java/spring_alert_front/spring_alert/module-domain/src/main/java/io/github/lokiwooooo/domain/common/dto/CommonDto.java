package io.github.lokiwooooo.domain.common.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@MappedSuperclass
public class CommonDto implements Serializable {

    Boolean isUse;
    LocalDateTime createdOn;
    String createdUserName;
    LocalDateTime lastEditedOn;
    String lastEditedUserName;

}
