package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() throws Exception{
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

        // persist 영속성 컨텍스트에 담은 후 flush -> 강제로 db insert 처리 clear -> db query날리고 영속성 컨텍스트 캐시 비움
        em.flush();
        em.clear();

        List<Member> selectMFromMemberM =
                em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : selectMFromMemberM){
            System.out.println("member =>" + member);
            System.out.println("member.team =>" + member.getTeam());
        }

    }


    @Test
    public void JpaEventBaseEntity() throws Exception{
        //given
        Member member = new Member("Member1");
        memberRepository.save(member); //@Prepersist

        Thread.sleep(100);
        member.setUsername("Member2");

        em.flush(); //@Preupdate
        em.clear();

        //when
        Member findMember = memberRepository.findById(member.getId()).get();

        //then
        System.out.println("findMember create date = "+ findMember.getCreatedDate());
        System.out.println("findMember update date = "+ findMember.getUpdatedDate());

    }
}