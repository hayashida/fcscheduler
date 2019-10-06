package repositories;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import models.Place;
import play.db.jpa.JPAApi;

public class PlaceRepository extends AppRepository {
  @Inject
  public PlaceRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
    this.jpaApi = jpaApi;
    this.executionContext = executionContext;
  }

  public CompletionStage<List<Place>> list() {
    return supplyAsync(() -> withTransaction(em -> list(em)), executionContext);
  }

  private List<Place> list(EntityManager em) {
    return em.createQuery("select p from Place p order by p.no asc", Place.class).getResultList();
  }

  public CompletionStage<Place> get(int id) {
    return supplyAsync(() -> withTransaction(em -> get(em, id)), executionContext);
  }

  private Place get(EntityManager em, int id) {
    return em.find(Place.class, id);
  }

  public CompletionStage<Place> add(Place place) {
    return supplyAsync(() -> withTransaction(em -> add(em, place)), executionContext);
  }

  private Place add(EntityManager em, Place place) {
    em.persist(em);
    return place;
  }

  public CompletionStage<Place> update(Place place) {
    return supplyAsync(() -> withTransaction(em -> update(em, place)), executionContext);
  }

  private Place update(EntityManager em, Place place) {
    em.merge(place);
    return place;
  }

  public CompletionStage<Place> delete(int id) {
    return supplyAsync(() -> withTransaction(em -> delete(em, id)), executionContext);
  }

  private Place delete(EntityManager em, int id) {
    Place place = get(em, id);
    em.remove(place);
    return place;
  }
}
