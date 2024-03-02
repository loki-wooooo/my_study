package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public Team save(final Team team) throws Exception {
        em.persist(em);
        return team;
    }

    public void delete(final Team team) throws Exception {
        em.remove(team);
    }

    public List<Team> findAll() throws Exception {
        return em.createQuery("select t from Team t", Team.class).getResultList();
    }

    public Optional<Team> findById(final Long id) throws Exception {
        Team team = em.find(Team.class, id);
        //null일수도 있음
        return Optional.ofNullable(team);
    }

    public Long count() throws Exception {
        return em.createQuery("select count(t) from Team t", Long.class).getSingleResult();
    }

    public Team find(final Long id) throws Exception {
        return em.find(Team.class, id);
    }
}
