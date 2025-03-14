package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;

//@Repository => 생략 가능
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
