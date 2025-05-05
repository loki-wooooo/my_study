package io.github.lokiwooooo.domain.log.entity;

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
@Entity(name = "LOG")
@Table(name = "TB_LOG")
@SuperBuilder
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Log extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "LOG_ID", nullable = false, updatable = false)
    @Comment("Log Id")
    String logId;

    @Column(name = "LOG_TYPE", nullable = true)
    @Comment("Log 타입")
    @Enumerated(EnumType.STRING)
    LogType logType;

    @Column(name = "LOG_DETAIL_TYPE", nullable = true)
    @Comment("Log 상세 타입")
    @Enumerated(EnumType.STRING)
    LogDetailType logDetailType;

    @Column(name = "LOG_CONTENT", columnDefinition = "TEXT", nullable = true)
    @Comment("Log 내용")
    String logContent;

    @Column(name = "LOG_IP", length = 5000, nullable = true)
    @Comment("Log IP")
    String ip;

}
