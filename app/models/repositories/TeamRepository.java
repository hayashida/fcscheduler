package models.repositories;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import models.entities.TeamEntity;
import play.db.jpa.JPAApi;

public class TeamRepository {
  private JPAApi jpaApi;
  private DatabaseExecutionContext executionContext;

  @Inject
  public TeamRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  private <T> T withTransaction(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }

  public CompletionStage<List<TeamEntity>> list() {
    return supplyAsync(() -> withTransaction(em -> list(em)), executionContext);
  }

  private List<TeamEntity> list(EntityManager em) {
    return em.createQuery("select p from TeamEntity p", TeamEntity.class).getResultList();
  }

  public CompletionStage<TeamEntity> get(int id) {
    return supplyAsync(() -> withTransaction(em -> get(em, id)), executionContext);
  }

  private TeamEntity get(EntityManager em, int id) {
    return em.find(TeamEntity.class, id);
  }

  public CompletionStage<TeamEntity> add(TeamEntity teamEntity) {
    return supplyAsync(() -> withTransaction(em -> add(em, teamEntity)), executionContext);
  }

  private TeamEntity add(EntityManager em, TeamEntity teamEntity) {
    em.persist(teamEntity);
    return teamEntity;
  }

  public CompletionStage<TeamEntity> update(TeamEntity teamEntity) {
    return supplyAsync(() -> withTransaction(em -> update(em, teamEntity)), executionContext);
  }

  private TeamEntity update(EntityManager em, TeamEntity teamEntity) {
    em.merge(teamEntity);
    return teamEntity;
  }

  public CompletionStage<TeamEntity> delete(int id) {
    return supplyAsync(() -> withTransaction(em -> delete(em, id)), executionContext);
  }

  private TeamEntity delete(EntityManager em, int id) {
    TeamEntity teamEntity = get(em, id);
    em.remove(teamEntity);
    return teamEntity;
  }
}
