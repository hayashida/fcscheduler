package services;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import models.Place;
import play.db.jpa.JPAApi;
import repositories.PlaceRepository;

public class PlaceService extends AppService {
  private final PlaceRepository placeRepository;

  @Inject
  public PlaceService(
      PlaceRepository placeRepository, JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.placeRepository = placeRepository;
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  public CompletionStage<List<Place>> list() {
    return supplyAsync(() -> withTransaction(em -> placeRepository.list(em)), executionContext);
  }

  public CompletionStage<Place> get(int id) {
    return supplyAsync(() -> withTransaction(em -> placeRepository.get(em, id)), executionContext);
  }

  public CompletionStage<Place> add(Place place) {
    return supplyAsync(
        () -> withTransaction(em -> placeRepository.add(em, place)), executionContext);
  }

  public CompletionStage<Place> update(Place place) {
    return supplyAsync(
        () -> withTransaction(em -> placeRepository.update(em, place)), executionContext);
  }

  public CompletionStage<Place> delete(int id) {
    return supplyAsync(
        () -> withTransaction(em -> placeRepository.delete(em, id)), executionContext);
  }
}
