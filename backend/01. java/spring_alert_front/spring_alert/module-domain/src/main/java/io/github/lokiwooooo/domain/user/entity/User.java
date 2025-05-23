package io.github.lokiwooooo.domain.user.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "tb_user"
)
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    @Comment("사용자 ID PK")
    UUID id;

    @Column(name = "name", nullable = false, length = 50)
    @Comment("사용자명")
    String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    @Comment("사용자 E-Mail")
    String email;

    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    @Comment("사용자 전화번호")
    String phoneNumber;
}
