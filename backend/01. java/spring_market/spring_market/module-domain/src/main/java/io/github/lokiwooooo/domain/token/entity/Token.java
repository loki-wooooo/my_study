package io.github.lokiwooooo.domain.token.entity;

import io.github.lokiwooooo.domain.common.dto.CommonDto;
import io.github.lokiwooooo.domain.user.entity.User;
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
@Entity(name = "TOKEN")
@Table(name = "TB_TOKEN")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Token extends CommonDto {

    @Id
    @Column(name = "TOKEN_ID", nullable = false, updatable = false)
    @Comment("토큰 ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "ACCESS_TOKEN", nullable = true, length = 1000)
    @Comment("Access Token")
    String accessToken;

    @Column(name = "REFRESH_TOKEN", nullable = true, length = 1000)
    @Comment("Refresh TokeTO")
    String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", unique = true)
    User user = new User();

}
