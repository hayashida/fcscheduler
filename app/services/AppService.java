package services;

import java.util.function.Function;
import javax.persistence.EntityManager;
import play.db.jpa.JPAApi;

public class AppService {
  protected JPAApi jpaApi;
  protected DatabaseExecutionContext executionContext;

  protected <T> T withTransaction(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }
}
