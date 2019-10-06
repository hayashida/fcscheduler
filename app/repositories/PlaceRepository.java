package repositories;

import java.util.List;
import javax.persistence.EntityManager;
import models.Place;

public class PlaceRepository extends AppRepository {
  public List<Place> list(EntityManager em) {
    return em.createNamedQuery("Place.findAll", Place.class).getResultList();
  }

  public Place get(EntityManager em, int id) {
    return em.createNamedQuery("Place.findById", Place.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public Place add(EntityManager em, Place place) {
    em.persist(place);
    return place;
  }

  public Place update(EntityManager em, Place place) {
    em.merge(place);
    return place;
  }

  public Place delete(EntityManager em, int id) {
    Place place = get(em, id);
    em.remove(place);
    return place;
  }
}
