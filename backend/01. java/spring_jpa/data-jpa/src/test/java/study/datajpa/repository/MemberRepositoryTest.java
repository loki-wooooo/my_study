package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional  //test 후 롤백
// @Rollback(false) // 롤백 x
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testMember() throws Exception{

        // Given
        Member member = new Member("memberA");
        // When
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        // Then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void basicCRUD() throws Exception{

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // update
        // 더티체킹(변경감지) : Transaction 안에서 엔티티의 변경이 일어나면, 변경 내용을 자동으로 데이터베이스에 반영
//        findMember1.setUsername("Member!!!!!!");
//
//
//        // 리스트 검증 조회
//        List<Member> all = memberRepository.findAll();
//        assertThat(all.size()).isEqualTo(2);
//
//        // 카운트 검증
//        Long count = memberRepository.count();
//        assertThat(count).isEqualTo(2);
//
//        // 삭제 검증
//        memberRepository.delete(member1);
//        memberRepository.delete(member2);
//
//        Long deleteCount = memberRepository.count();
//        assertThat(count).isEqualTo(0);


    }

    @Test
    public void findByUsernameAndGreaterThen() throws Exception{
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("aaa", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> memberList = memberRepository.findByUsernameAndAgeGreaterThen("aaa", 15);
        assertThat(memberList.get(0).getUsername()).isEqualTo(m1.getUsername());
        assertThat(memberList.get(0).getAge()).isEqualTo(20);
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    public void testNamedQuery() throws Exception{
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> memberList = memberRepository.findByUsername("AAA");
        Member findMember = memberList.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testQuery() throws Exception{
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> memberList = memberRepository.findUser("AAA", 10);
        Member findMember = memberList.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void findUsernameList() throws Exception{
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsername();
        for (String s : usernameList) {
            System.out.println("name = "+s);
        }
    }

    @Test
    public void findMemberDto() throws Exception{
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDtoList = memberRepository.findMemberDto();
        for (MemberDto dto : memberDtoList) {
            System.out.println("dto = "+dto);
        }
    }

    @Test
    public void findByNames() throws Exception{
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBBB"));
        for (Member m : result){
            System.out.println("name = "+ m.getUsername());
        }
    }

    @Test
    public void returnType() throws Exception{
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        //return Data가 없으면 null 이 아닌 empty로 나옴 -> 빈 컬렉션 반환
        // ex) if(aaaList != null){} 요런것 사용 지양
        List<Member> aaaList = memberRepository.findListByUsername("AAA");
        Member aaaMember = memberRepository.findMemberByUsername("AAA");
        Optional<Member> aaaOptional = memberRepository.findOptionalByUsername("AAA");
    }

    @Test
    public void bulkUpdate() throws Exception {

        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        // when

        /*
         * 영속성 컨텍스트를 무시하고 DB에 바로 변경해서
         * 해당 내용을 반영하려면 영속성 컨텍스트를 날려야함
         * (벌크 연산 이후에 해당 영속성 컨텍스트 날리는게 좋음)
         * */
        Integer integer = memberRepository.bulkAgePlus(20);

        // 남아있지 않는 내용을 DB에 반영
        entityManager.flush();

        // Entity Manager 영속성 컨텍스트를 날림
        entityManager.clear();

        List<Member> result = memberRepository.findByUsername("member5");
        Member member = result.get(0);
        System.out.println("member5 = " + member);

    }

    @Test
    public void findMemberLazy() throws Exception{
        //given
        //member1 -> teamA
        //member2 -> teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        entityManager.flush();
        entityManager.clear();

//        //when  N + 1
//        //select member
//        //step.1 member 조회함 (1)
//        List<Member> members = memberRepository.findAll();
//
//        // 이시점에 team 조회 (N)
//        for (Member member : members){
//            System.out.println("member = " + member.getUsername());
//            System.out.println("member.teamClass = " + member.getTeam().getClass());
//            System.out.println("member.team = " + member.getTeam().getName());
//        }

        //when  N + 1 -> fetch join으로 해결
        //select member
        //step.1 member 조회함 (1)
        // 가짜 프록시 객체가 아닌 진짜
        List<Member> members = memberRepository.findMemberFetchJoin();

        // 이시점에 team 조회 (N)
        for (Member member : members){
            System.out.println("member = " + member.getUsername());
            System.out.println("member.teamClass = " + member.getTeam().getClass());
            System.out.println("member.team = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint() throws Exception{
        // given
        Member member1 = memberRepository.save(new Member("member1", 10));
        entityManager.flush(); // JPA 1차 캐시 지우기 X -> 동기화만 처리함
        entityManager.clear(); // 캐시 삭제

        // when
        // 조회용으로만 사용할시
        // hints 사용시 해당 내용의 변경 X
        Member findMember = memberRepository.findById(member1.getId()).get();
        findMember.setUsername("member2");

        entityManager.flush();
    }


    @Test
    public void lock() throws Exception{
        // given
        Member member1 = memberRepository.save(new Member("member1", 10));
        entityManager.flush(); // JPA 1차 캐시 지우기 X -> 동기화만 처리함
        entityManager.clear(); // 캐시 삭제

        // when
        List<Member> member11 = memberRepository.findLockByUsername("member1");
    }

    @Test
    public void callCustom() {
        //신규로 만든 MemberRepositoryCustom에서 불러옴
        List<Member> result = memberRepository.findMemberCustom();
    }



}