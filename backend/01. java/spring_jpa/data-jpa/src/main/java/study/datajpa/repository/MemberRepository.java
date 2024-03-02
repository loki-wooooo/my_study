package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
    // 개인적으로 1~2개 정도 까지는 사용하는데, 나머지는 JPQL사용
    List<Member> findByUsernameAndAgeGreaterThen(final String username, final Integer age) throws Exception;

    List<Member> findTop3HelloBy();

    // 해당 내용이 없어도 잘 동작함
    // 실무에서 사용 많이 안함
    //Member.findByUsername -> 실행(named query를 먼저 찾아봄)
//    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") final String username) throws Exception;

    // 실무에서 많이 사용함
    // 이름이 없는 named query
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") final String username, @Param("age") final Integer age) throws Exception;

    @Query("select m.username from Member m")
    List<String> findUsername();

    // new operation 사용
    // JPQL 에서 사용하는 문법
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();
}
