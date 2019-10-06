package repositories;

import java.util.function.Function;
import javax.persistence.EntityManager;
import play.db.jpa.JPAApi;

public class AppRepository {
  protected JPAApi jpaApi;
  protected DatabaseExecutionContext executionContext;

  protected <T> T withTransaction(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }
}
