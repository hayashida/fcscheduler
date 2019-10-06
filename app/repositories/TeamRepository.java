package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import models.Team;

public class TeamRepository extends AppRepository {
  public List<Team> list(EntityManager em) {
    return em.createNamedQuery("Team.findAll", Team.class).getResultList();
  }

  public Team get(EntityManager em, int id) {
    return em.createNamedQuery("Team.findById", Team.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public Team add(EntityManager em, Team team) {
    em.persist(team);
    return team;
  }

  public Team update(EntityManager em, Team team) {
    em.merge(team);
    return team;
  }

  public Team delete(EntityManager em, int id) {
    Team team = get(em, id);
    em.remove(team);
    return team;
  }
}
