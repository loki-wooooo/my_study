package study.querydsl.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;
import study.querydsl.entity.Member;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

// xxx"Impl" 로 생성
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryCustomImpl(EntityManager em) throws Exception{
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    /**
     * 방법 추천
     * */
    public List<MemberTeamDto> search(MemberSearchCondition condition) throws Exception{

        return jpaQueryFactory
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

    @Override
    public Page<MemberTeamDto> searchPageSimple(final MemberSearchCondition condition, final Pageable pageable) throws Exception {

        QueryResults<MemberTeamDto> results = jpaQueryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()) //한페이지에 몇개를 갖고오는지
                .fetchResults();

        // content - data
        List<MemberTeamDto> content = results.getResults();

        // count
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) throws Exception {

        // content query
        List<MemberTeamDto> content = jpaQueryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()) //한페이지에 몇개를 갖고오는지
                .fetch();

        // total query - 최적화
        JPAQuery<Member> countQuery = jpaQueryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team)

                // 코드의 재사용이 가능함
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeLoe()),
                        ageLoe(condition.getAgeLoe())
                );

        // 메서드 호출시 실제 count가 날라감
        //countQuery.fetch().size();

//        return new PageImpl<>(content, pageable, total);

        // 조건이 만족할 때만 실행함
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
    }

    /**
     * Predicate > BooleanExpression
     * BooleanExpression -> 조합이 가능함
     * */

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
