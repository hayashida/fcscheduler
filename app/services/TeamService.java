package services;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import models.Team;
import play.db.jpa.JPAApi;
import repositories.TeamRepository;

public class TeamService extends AppService {
  private final TeamRepository teamRepository;

  @Inject
  public TeamService(
      TeamRepository teamRepository, JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.teamRepository = teamRepository;
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  public CompletionStage<List<Team>> list() {
    return supplyAsync(() -> withTransaction(em -> teamRepository.list(em)), executionContext);
  }

  public CompletionStage<Team> get(int id) {
    return supplyAsync(() -> withTransaction(em -> teamRepository.get(em, id)), executionContext);
  }

  public CompletionStage<Team> add(Team team) {
    return supplyAsync(() -> withTransaction(em -> teamRepository.add(em, team)), executionContext);
  }

  public CompletionStage<Team> update(Team team) {
    return supplyAsync(
        () -> withTransaction(em -> teamRepository.update(em, team)), executionContext);
  }

  public CompletionStage<Team> delete(int id) {
    return supplyAsync(
        () -> withTransaction(em -> teamRepository.delete(em, id)), executionContext);
  }
}
