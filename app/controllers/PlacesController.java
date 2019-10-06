package controllers;

import javax.inject.Inject;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

public class PlacesController extends ApplicationController {
  private final HttpExecutionContext ec;

  @Inject
  public PlacesController(HttpExecutionContext ec) {
    this.ec = ec;
  }

  public Result index() {
    return TODO();
  }

  public Result add() {
    return TODO();
  }

  public Result create() {
    return redirect(routes.PlacesController.index());
  }

  public Result show(int id) {
    return TODO();
  }

  public Result edit(int id) {
    return TODO();
  }

  public Result update(int id) {
    return redirect(routes.PlacesController.index());
  }

  public Result remove(int id) {
    return redirect(routes.PlacesController.index());
  }
}
