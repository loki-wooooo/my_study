package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(final Member member) throws Exception {
        em.persist(member);
        return member;
    }

    public void delete(final Member member) throws Exception {
        em.remove(member);
    }

    public List<Member> findAll() throws Exception {
        //JPQL
        //객체를 대상으로 하는 쿼리
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public Optional<Member> findById(final Long id) throws Exception {
        Member member = em.find(Member.class, id);
        //null일수도 있음
        return Optional.ofNullable(member);
    }

    public Long count() throws Exception {
        return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();
    }

    public Member find(final Long id) throws Exception {
        return em.find(Member.class, id);
    }

    public List<Member> findByUsernameAndAgeGreaterThen(final String username, int age) throws Exception {
        return em.createQuery(" select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList()
                ;
    }

    public List<Member> findByPage(int age, int offset, int limit) throws Exception {
        return em.createQuery("select m from Member where m.age = :age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Long totalCount(int age) throws Exception {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    public Integer bulkAgePlus(final Integer age) throws Exception {
        Integer resultCount = em.createQuery("update Member m set m.age = m.age+1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();

        return resultCount;
    }

}
