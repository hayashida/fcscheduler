package controllers;

import forms.TeamForm;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import models.entities.TeamEntity;
import models.repositories.TeamRepository;
import play.data.*;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

public class TeamsController extends Controller {

  private final Form<TeamForm> teamForm;
  private final FormFactory formFactory;
  private final TeamRepository teamRepository;
  private final HttpExecutionContext ec;

  @Inject
  public TeamsController(
      FormFactory formFactory, TeamRepository teamRepository, HttpExecutionContext ec) {
    this.formFactory = formFactory;
    this.teamForm = formFactory.form(TeamForm.class);
    this.teamRepository = teamRepository;
    this.ec = ec;
  }

  public CompletionStage<Result> index() {
    return teamRepository
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
    Form form = formFactory.form(TeamEntity.class).bindFromRequest();

    try {
      TeamEntity teamEntity = (TeamEntity) form.get();
      return teamRepository
          .add(teamEntity)
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
    return teamRepository
        .get(id)
        .thenApplyAsync(
            team -> {
              return ok(views.html.teams.show.render(team, id));
            },
            ec.current());
  }

  public CompletionStage<Result> edit(int id) {
    return teamRepository
        .get(id)
        .thenApplyAsync(
            team -> {
              TeamForm form = new TeamForm(id);
              form.setNo(team.getNo());
              form.setName(team.getName());
              Form<TeamForm> formdata = teamForm.fill(form);
              return ok(views.html.teams.edit.render(formdata, id));
            },
            ec.current());
  }

  public CompletionStage<Result> update(int id) {
    Form form = formFactory.form(TeamEntity.class).bindFromRequest();

    try {
      TeamEntity team = (TeamEntity) form.get();
      team.setId(id);

      return teamRepository
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
    return teamRepository
        .delete(id)
        .thenApplyAsync(
            p -> {
              return redirect(routes.TeamsController.index());
            },
            ec.current());
  }
}
