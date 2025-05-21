package io.github.lokiwooooo.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class CommonEntity implements Serializable {

    @Column(name = "IS_USE", nullable = false)
    @Comment("사용 여부")
    @Builder.Default
    Boolean isUse = true;

    @CreatedDate
    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    @Comment("생성일")
    LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "CREATED_USER_NAME", length = 200, nullable = true, updatable = false)
    @Comment("생성자명")
    @Builder.Default
    String createdUserName = "SYSTEM_USER";

    @LastModifiedDate
    @Column(name = "LAST_EDITED_ON", nullable = false)
    @Comment("수정일")
    LocalDateTime lastEditedOn;

    @LastModifiedBy
    @Column(name = "LAST_EDITED_USER_NAME", length = 200, nullable = true)
    @Comment("수정자명")
    @Builder.Default
    String lastEditedUserName = "SYSTEM_USER";

}
