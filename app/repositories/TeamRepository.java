package repositories;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import models.Team;
import play.db.jpa.JPAApi;

public class TeamRepository extends AppRepository {
  @Inject
  public TeamRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  public CompletionStage<List<Team>> list() {
    return supplyAsync(() -> withTransaction(em -> list(em)), executionContext);
  }

  private List<Team> list(EntityManager em) {
    return em.createQuery("select p from Team p order by p.no asc", Team.class).getResultList();
  }

  public CompletionStage<Team> get(int id) {
    return supplyAsync(() -> withTransaction(em -> get(em, id)), executionContext);
  }

  private Team get(EntityManager em, int id) {
    return em.find(Team.class, id);
  }

  public CompletionStage<Team> add(Team team) {
    return supplyAsync(() -> withTransaction(em -> add(em, team)), executionContext);
  }

  private Team add(EntityManager em, Team team) {
    em.persist(team);
    return team;
  }

  public CompletionStage<Team> update(Team team) {
    return supplyAsync(() -> withTransaction(em -> update(em, team)), executionContext);
  }

  private Team update(EntityManager em, Team team) {
    em.merge(team);
    return team;
  }

  public CompletionStage<Team> delete(int id) {
    return supplyAsync(() -> withTransaction(em -> delete(em, id)), executionContext);
  }

  private Team delete(EntityManager em, int id) {
    Team team = get(em, id);
    em.remove(team);
    return team;
  }
}
