package io.github.lokiwooooo.domain.user.entity;

import io.github.lokiwooooo.domain.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USER")
@Table(name = "TB_USER")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class User extends CommonEntity {

    @Id
    @Column(name = "USER_ID", nullable = false, updatable = false)
    @Comment("사용자 ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "USER_NAME", nullable = false, unique = true, length = 200)
    @Comment("사용자 명")
    String name;

    @Column(name = "USER_PASSWORD", nullable = false)
    @Comment("사용자 비밀번호")
    String password;

    @Column(name = "USER_EMAIL", nullable = true, length = 250)
    @Comment("사용자 E-Mail")
    String email;

}
