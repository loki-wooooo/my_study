package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional  //test 후 롤백
// @Rollback(false) // 롤백 x
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() throws Exception {

        // Given
        Member member = new Member("memberA");

        // When
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(savedMember.getId());

        // Then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void basicCRUD() throws Exception {

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // 단건 조회 검증
        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // update
        // 더티체킹(변경감지) : Transaction 안에서 엔티티의 변경이 일어나면, 변경 내용을 자동으로 데이터베이스에 반영
        findMember1.setUsername("Member!!!!!!");


//        // 리스트 검증 조회
//        List<Member> all = memberJpaRepository.findAll();
//        assertThat(all.size()).isEqualTo(2);
//
//        // 카운트 검증
//        Long count = memberJpaRepository.count();
//        assertThat(count).isEqualTo(2);
//
//        // 삭제 검증
//        memberJpaRepository.delete(member1);
//        memberJpaRepository.delete(member2);
//
//        Long deleteCount = memberJpaRepository.count();
//        assertThat(count).isEqualTo(0);


    }

    @Test
    public void findByUsernameAndGreaterThen() throws Exception {
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("aaa", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> memberList = memberJpaRepository.findByUsernameAndAgeGreaterThen("aaa", 15);
        assertThat(memberList.get(0).getUsername()).isEqualTo(m1.getUsername());
        assertThat(memberList.get(0).getAge()).isEqualTo(20);
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    public void paging() throws Exception {
        for (int i = 0; i < 5; i++) {
            memberJpaRepository.save(new Member("member" + 0, 10));
        }

        Integer age = 10;
        Integer offset = 0;
        Integer limit = 3;

        // when
        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        Long totalCount = memberJpaRepository.totalCount(age);


        // then
        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);
    }

    @Test
    public void bulkUpdate() throws Exception {

        // given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 19));
        memberJpaRepository.save(new Member("member3", 20));
        memberJpaRepository.save(new Member("member4", 21));
        memberJpaRepository.save(new Member("member5", 40));

        // when
        Integer integer = memberJpaRepository.bulkAgePlus(20);

        // then
        assertThat(integer).isEqualTo(3);

    }


}