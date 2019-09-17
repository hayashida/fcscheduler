package controllers;

import play.mvc.*;

public class SchedulesController extends Controller {
  public Result index() {
    return ok(views.html.schedules.index.render());
  }

  public Result create() {
    return TODO();
  }

  public Result edit(int id) {
    return TODO();
  }
}
