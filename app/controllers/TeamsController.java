package controllers;

import forms.TeamForm;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import models.Team;
import play.data.*;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import services.TeamService;

public class TeamsController extends ApplicationController {
  private final Form<TeamForm> teamForm;
  private final FormFactory formFactory;
  private final TeamService service;
  private final HttpExecutionContext ec;

  @Inject
  public TeamsController(FormFactory formFactory, TeamService service, HttpExecutionContext ec) {
    this.formFactory = formFactory;
    this.teamForm = formFactory.form(TeamForm.class);
    this.service = service;
    this.ec = ec;
  }

  public CompletionStage<Result> index() {
    return service
        .list()
        .thenApplyAsync(
            teams -> {
              return ok(views.html.teams.index.render(teams));
            },
            ec.current());
  }

  public Result add() {
    return ok(views.html.teams.add.render(teamForm));
  }

  public CompletionStage<Result> create() {
    Form form = formFactory.form(Team.class).bindFromRequest();

    try {
      Team team = (Team) form.get();
      return service
          .add(team)
          .thenApplyAsync(
              p -> {
                return redirect(routes.TeamsController.index());
              },
              ec.current());
    } catch (IllegalStateException e) {
      return CompletableFuture.completedFuture(ok(views.html.teams.add.render(form)));
    }
  }

  public CompletionStage<Result> show(int id) {
    return service
        .get(id)
        .thenApplyAsync(
            team -> {
              return ok(views.html.teams.show.render(team, id));
            },
            ec.current());
  }

  public CompletionStage<Result> edit(int id) {
    return service
        .get(id)
        .thenApplyAsync(
            team -> {
              TeamForm form = new TeamForm(id);
              form.setNo(team.no);
              form.setName(team.name);

              Form<TeamForm> formdata = teamForm.fill(form);
              return ok(views.html.teams.edit.render(formdata, id));
            },
            ec.current());
  }

  public CompletionStage<Result> update(int id) {
    Form form = formFactory.form(Team.class).bindFromRequest();

    try {
      Team team = (Team) form.get();
      team.id = id;

      return service
          .update(team)
          .thenApplyAsync(
              p -> {
                return redirect(routes.TeamsController.index());
              },
              ec.current());
    } catch (IllegalStateException e) {
      return CompletableFuture.completedFuture(ok(views.html.teams.edit.render(form, id)));
    }
  }

  public CompletionStage<Result> remove(int id) {
    return service
        .delete(id)
        .thenApplyAsync(
            p -> {
              return redirect(routes.TeamsController.index());
            },
            ec.current());
  }
}
