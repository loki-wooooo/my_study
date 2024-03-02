package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void before() {
        jpaQueryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {
        // member1을 찾아라.
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQueryDsl() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        // m -> 별칭 (table에서 as "M")
//        QMember m = new QMember("m");
//        QMember m = QMember.member;
//        Member findMember = jpaQueryFactory
//                .select(m)
//                .from(m)
//                .where(m.username.eq("member1")) //sql에서 파라미터로 자동 바인딩 함
//                .fetchOne();

        // static variable로 변경 * 권장
        Member findMember = jpaQueryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1")) //sql에서 파라미터로 자동 바인딩 함
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1")
                                .and(member.age.eq(10))
                ).fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

    }

    @Test
    public void searchAndParam() {
        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(
                        member.username.eq("member1")
                                .and(member.age.between(10, 30))
                ).fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

    }

    @Test
    public void resultFetchTest() {
        List<Member> fetch = jpaQueryFactory
                .selectFrom(member)
                .fetch();

        Member fetchOne = jpaQueryFactory.selectFrom(member).fetchOne();

        Member fetchFirst = jpaQueryFactory.selectFrom(member).fetchFirst(); // limit(1).fetchOne()

        QueryResults<Member> memberQueryResults = jpaQueryFactory.selectFrom(member).fetchResults();
        memberQueryResults.getTotal();
        List<Member> results = memberQueryResults.getResults();


        long total = jpaQueryFactory.selectFrom(member).fetchCount();
    }

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 올림차순(asc)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
     */
    @Test
    public void sort() throws Exception {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }

    @Test
    public void paging1() throws Exception {
        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 1개의 데이터 skip
                .limit(2) // 최대 2건
                .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2() throws Exception {
        QueryResults<Member> memberQueryResults = jpaQueryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1) // 1개의 데이터 skip
                .limit(2) // 최대 2건
                .fetchResults();

        assertThat(memberQueryResults.getTotal()).isEqualTo(4);
        assertThat(memberQueryResults.getLimit()).isEqualTo(2);
        assertThat(memberQueryResults.getOffset()).isEqualTo(1);
        assertThat(memberQueryResults.getResults().size()).isEqualTo(2);
    }

    @Test
    public void aggregation() throws Exception {
        /**
         * 회원의 카운트
         * 회원 나이 합
         * 회원 나이 평균
         * 회원 나이 최대
         * 회원 나이 최소
         *
         * Tuple - querydsl의 Tuple
         * 여러개의 타입을 막 꺼내올 수 있음
         * 실무에서 많이 쓰지 않고 DTO로 바로 꺼내올 수 있음
         * */
        List<Tuple> fetch = jpaQueryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = fetch.get(0);
        //위에 사용한 내용을 그대로 사
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.sum())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    /**
     * 팀 이름과 각팀의 평균 연령을 구하라.
     */
    @Test
    public void group() throws Exception {

        List<Tuple> fetch = jpaQueryFactory
                .select(
                        team.name, member.age.avg()
                )
                .from(member)
                .join(member.team, team) //join
                .groupBy(team.name)
                .fetch();

        Tuple teamA = fetch.get(0);
        Tuple teamB = fetch.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15); //(10 + 20) /2

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35); //(30 + 40) /2
    }

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    public void join() throws Exception {
        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team) //QTeam, default inner join, leftJoin, rightJoin, innerJoin
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인
     * 연관관계가 없는 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void theta_join() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        // from절에 여러 테이블을 조인
        // 외부조인(outer join)이 불가능함
        // join "on" 절을 사용하면 외부조인(outer join) 가능함
        List<Member> result = jpaQueryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");

    }

    /**
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     * JPQL : select m, t from Member m left join m.team t on t.name = 'teamA'
     */
    @Test
    public void join_on_filtering() throws Exception {
        List<Tuple> result = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(team.name.eq("teamA"))

//                .join(member.team, team)
//                .where(team.name.eq("teamA")) //inner join 시 on 절을 사용한것 과 똑같음
                .fetch();

        for (Tuple t : result) {
            System.out.println("tuple = " + t);
        }
    }

    /**
     * 연관관계가 없는 엔티티를 외부 조인
     * 회원의 이름이 팀 이름과 같은 대상을 외부 조
     */
    @Test
    public void join_on_no_relation() throws Exception {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        // from절에 여러 테이블을 조인
        // 외부조인(outer join)이 불가능함
        // join "on" 절을 사용하면 외부조인(outer join) 가능함
        List<Tuple> result = jpaQueryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team) //막 조인시 해당 member.team X team 엔티티를 그냥 넣음, (member.team, tema)을 사용 안할 시 id를 매칭해서 사용하지 못함
                .on(member.username.eq(team.name))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");

    }

    //entity manager를 만드는 팩토리
    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoinNo() throws Exception {
        // 영속성컨텍스트에 담겨 있으면 결과를 빨리 보기 힘듬
        em.flush();
        em.clear();

        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        //findMember.getTeam() - 초기화가 됐는지 안됐는지 알려주는 엔티티
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치 조인 미적용").isFalse();
    }

    @Test
    public void fetchJoinUse() throws Exception {
        // 영속성컨텍스트에 담겨 있으면 결과를 빨리 보기 힘듬
        em.flush();
        em.clear();

        Member findMember = jpaQueryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin() // fetch join 시 해당 내용을 다 갖고옴 (team entity 까지)
                .where(member.username.eq("member1"))
                .fetchOne();

        //findMember.getTeam() - 초기화가 됐는지 안됐는지 알려주는 엔티티
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치 조인 미적용").isTrue();
    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    public void subQuery() throws Exception {

        //member entity가 중복되지 않게 사용
        QMember memberSub = new QMember("memberSub");

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        // subquery
                        select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원 조회
     */
    @Test
    public void subQueryGoe() throws Exception {

        //member entity가 중복되지 않게 사용
        QMember memberSub = new QMember("memberSub");

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        // subquery
                        select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(30, 40);
    }

    /**
     * 나이가 평균 이상인 회원 조회
     */
    @Test
    public void subQueryIn() throws Exception {

        //member entity가 중복되지 않게 사용
        QMember memberSub = new QMember("memberSub");

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .where(member.age.in(
                        // subquery
                        select(memberSub.age)
                                .from(memberSub)
                                .where(member.age.gt(10))
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(20, 30, 40);
    }

    @Test
    public void selectSubQuery() throws Exception {

        //member entity가 중복되지 않게 사용
        QMember memberSub = new QMember("memberSub");

        List<Tuple> result = jpaQueryFactory
                .select(member.username,
                        //static import 가능
                        select(memberSub.age.avg())
                                .from(memberSub)
                ).from(member)
                .fetch();

        for (Tuple t : result) {
            System.out.println("tuple = " + t);
        }
    }

    /**
     * DB에서 해결을 안함
     * 데이터의 전환은 JAVA에서 처리함(Application 쪽에서 처리)
     */
    @Test
    public void basicCase() throws Exception {
        List<String> result = jpaQueryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void complexCase() throws Exception {
        List<String> result = jpaQueryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0 ~ 20 살")
                        .when(member.age.between(21, 30)).then("21 ~ 30 살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }

    }

    //상수 넣기
    @Test
    public void constant() throws Exception {
        List<Tuple> result = jpaQueryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        for (Tuple t : result) {
            System.out.println("tuple = " + t);
        }
    }

    //문자 더하기
    @Test
    public void concat() throws Exception {

        //{usename}_{age}
        List<String> result = jpaQueryFactory
//                .select(member.username.concat("_").concat(member.age)) //age는 상수여서 안됨
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }

    }

    @Test
    public void simpleProjection() throws Exception {
        List<String> result = jpaQueryFactory
                .select(member.username)
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void tupleProjection() throws Exception {

        //Tuple repository, dao - o
        //Tuple service, controller - x

        List<Tuple> result = jpaQueryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple t : result) {
            String username = t.get(member.username);
            Integer age = t.get(member.age);

            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }

    @Test
    public void findDtoByJPQL() throws Exception {
        //DTO의 생성자를 만듬
        //
        List<MemberDto> result = em.createQuery("select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByQueryDslSetter() throws Exception {
        List<MemberDto> result = jpaQueryFactory
                //bean - getter, setter
                .select(Projections.bean(
                        MemberDto.class
                        , member.username
                        , member.age)
                )
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByQueryDslFields() throws Exception {
        List<MemberDto> result = jpaQueryFactory
                //fields - dto field에 바로 넣어줌
                .select(Projections.fields(
                        MemberDto.class
                        , member.username
                        , member.age)
                )
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findUserDtoByQueryDslFields() throws Exception {
        QMember subMember = new QMember("memberSub");

        List<UserDto> result = jpaQueryFactory

                .select(Projections.fields(
                                UserDto.class
                                //DTO에 맞춰서 변경함
                                , member.username.as("name")
//                        , member.age)
                                //subquery 를 이용방법
                                , ExpressionUtils.as(
                                        JPAExpressions
                                                .select(subMember.age.max())
                                                .from(subMember), "age"
                                )
                        )
                )
                .from(member)
                .fetch();

        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    public void findDtoByQueryDslConstructor() throws Exception {
        List<MemberDto> result = jpaQueryFactory
                //constructor - 생성자 방식 타입이 맞아야 함
                .select(Projections.constructor(
                        MemberDto.class
                        , member.username
                        , member.age)
                )
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }


//    @Test
//    public void findDtoByQueryProjection() throws Exception {
//        jpaQueryFactory
//                .select(new QMemberDto(member.username, member.age))
//                .from(member)
//                .fetch()
//                ;
//    }

    @Test
    public void dynamicQuery_BooleanBuilder() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParam, ageParam);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(final String usernameCond, Integer ageCond) throws Exception {
        /**
         * 데이터값의 null에 따라 분기처리
         * */

        // 데이터의 초기값을 넣을 수 있음
        // ex) BooleanBuilder builder = new BooleanBuilder(member.username.eq(usernameCond));
        BooleanBuilder builder = new BooleanBuilder();
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }

        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return jpaQueryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();

    }

    @Test
    public void dynamicQuery_WhereParam() throws Exception {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(usernameParam, ageParam);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(final String usernameCond, final Integer ageCond) throws Exception {
        return jpaQueryFactory
                .selectFrom(member)

                // where에 조건이 null 이면 해당 조건은 무시됨
                // 각 메서드를 조립이 가능함
                .where(
                        usernameEq(usernameCond), ageEq(ageCond)
                        // allEq(usernameCond, ageCond)
                )
                .fetch()
                ;

    }

    // 나이 조건
    private BooleanExpression ageEq(final Integer ageCond) throws Exception{
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

    // 사용자 명 조건
    private BooleanExpression usernameEq(final String usernameCond) throws Exception {
        return usernameCond != null ? member.username.eq(usernameCond) : null;
    }

    // 광고 상태 isValid, 날짜가 IN : isServiceable
    private Predicate allEq(final String usernameCond, final Integer ageCond) throws Exception{
        return usernameEq(usernameCond).and(ageEq(ageCond));
    }


    /**
     * 회원의 나이가 28 밑이면 비회원으로 변경
     * member1 = 10 => 비회원
     * member2 = 20 => 비회원
     * member3 = 30 => 유지
     * member4 = 40 => 유지
     *
     * */
    @Test
//    @Commit // test 시 끝나면 rollback 방지
    public void bulkUpdate() throws Exception{

        // DB의 상태와 영속성 컨테스트의 상태가 다름
        // 벌크연산은 영속성 컨텍스트에 남아있지 않음
        // 영속선 컨텍스트를 무시하고 바로 DB에 접근해서 사용
        // JPA는 영속성 컨텍스트의 우선순위가 재일 높음
        Long count = jpaQueryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        // 영속성 컨텍스트를 비우고, 초기화 진행 후 사용
        em.flush();
        em.clear();

        List<Member> result = jpaQueryFactory
                .selectFrom(member)
                .fetch();

        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    /**
     * 회원의 나이를 +1
     * */
    @Test
    public void bulkAdd() throws Exception{
        jpaQueryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                //.set(member.age, member.age.multiply(2)) // 회원의 나이를 자기나 *2
                .execute();
    }

    /**
     * 18상 이상의 나이의 회원을 삭제
     * */
    @Test
    public void bulkDelete() throws Exception{
        jpaQueryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }

    @Test
    public void sqlFunction1() throws Exception{
        List<String> result = jpaQueryFactory
                .select(
                        Expressions.stringTemplate(
                                "function('replace', {0}, {1}, {2})", member.username, "member", "M")
                ).from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = "+ s);
        }
    }

    @Test
    public void sqlFunction2() throws Exception{
        List<String> result = jpaQueryFactory
                .select(member.username)
                .from(member)
//                .where(
//                        member.username.eq(
//                                Expressions.stringTemplate("function('lower', '{0})", member.username)
//                        )
                .where(
                        member.username.eq(member.username.lower()) // 변경가능
                )
                .fetch();

        for (String s : result) {
            System.out.println("s = "+ s);
        }
    }


}
