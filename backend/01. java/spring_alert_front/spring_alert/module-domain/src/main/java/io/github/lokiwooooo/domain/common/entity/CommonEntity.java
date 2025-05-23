package io.github.lokiwooooo.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
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

    @CreatedDate
    @Column(name = "created_on", nullable = false, updatable = false)
    @Comment("생성일")
    LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "last_edited_on", nullable = false)
    @Comment("수정일")
    LocalDateTime lastEditedOn;

}
