package study.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;

    // query dsl 사용
    // entity manger 필요함
    private final JPAQueryFactory queryFactory;

    //    public MemberJpaRepository(final EntityManager em, final JPAQueryFactory jpaQueryFactory) {
    public MemberJpaRepository(final EntityManager em) {
        this.em = em;

        //생성자로 받을 수 도 있고, Spring Bean으로 생성할 수 있음
        this.queryFactory = new JPAQueryFactory(em);
//        this.queryFactory = jpaQueryFactory;
    }

    public void save(final Member member) throws Exception {
        em.persist(member);
    }

    public Optional<Member> findById(final Long id) throws Exception {
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
    }

    public List<Member> findAll() throws Exception {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // JPA to QueryDSL find all
    public List<Member> findAll_QueryDsl() throws Exception {
        return queryFactory
                .selectFrom(member)
                .fetch()
                ;
    }

    public List<Member> findByUsername(final String username) throws Exception {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    // JPA to QueryDSL find all username
    public List<Member> findByUsername_QueryDsl(final String username) throws Exception {
        return queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }


    public List<MemberTeamDto> searchByBuilder(final MemberSearchCondition condition) throws Exception {

        BooleanBuilder builder = new BooleanBuilder();

        //text 값이 null, "" 둘다 대응
        if (hasText(condition.getUsername())) {
            builder.and(member.username.eq(condition.getUsername()));
        }

        if (hasText(condition.getTeamName())) {
            builder.and(team.name.eq(condition.getTeamName()));
        }

        if (condition.getAgeGoe() != null) {
            builder.and(member.age.goe(condition.getAgeGoe()));
        }

        if (condition.getAgeLoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }

        /**
         * DTO에서 필요한 컬럼을 한번에 갖고
         * 데이터가 많을 수록 limit 및 기본조건이 있어야 함
         * 페이징쿼리 들어가면 더 좋음(*)
         * */
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch()
                ;
    }

    /**
     * 방법 추천
     * */
    public List<MemberTeamDto> search(MemberSearchCondition condition) throws Exception{

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)

                // 코드의 재사용이 가능함
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeLoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetch()
                ;
    }

    /**
     * Predicate > BooleanExpression
     * BooleanExpression -> 조합이 가능함
     * */

    // 조립이 가능함
    private BooleanExpression ageBetween(final Integer ageLoe, final Integer ageGoe) throws Exception{
        return ageGoe(ageGoe).and(ageLoe(ageLoe));
    }

    private BooleanExpression usernameEq(final String username) throws Exception{
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression teamNameEq(final String teamName) throws Exception{
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageGoe(final Integer ageGoe) throws Exception{
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(final Integer ageLoe) throws Exception{
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
