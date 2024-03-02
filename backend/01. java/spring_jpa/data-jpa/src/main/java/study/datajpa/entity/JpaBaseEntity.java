package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass //속성을 공유하는 클래스 (진짜 상속관계는 아님)
public class JpaBaseEntity {

    // update 시 변경되지 않음
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    /**
     * prePersist - JPA 엔티티(Entity)가 비영속(new/transient) 상태에서 영속(managed) 상태가 되는 시점 이전에 실행
     * preUpdate - 영속 상태의 엔티티를 이용하여 데이터 업데이트를 수행하기 이전에 실행됩니다.
     * */

    @PrePersist
    public void prePersist() {
        // 겹치거나 강조할때 this사용 ide 를 쓰기 때문에
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    // update 하기전에 실행
    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
