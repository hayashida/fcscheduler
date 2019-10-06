package controllers;

import forms.PlaceForm;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import models.Place;
import play.data.*;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import repositories.PlaceRepository;

public class PlacesController extends ApplicationController {
  private final Form<PlaceForm> placeForm;
  private final FormFactory formFactory;
  private final PlaceRepository placeRepository;
  private final HttpExecutionContext ec;

  @Inject
  public PlacesController(
      FormFactory formFactory, PlaceRepository placeRepository, HttpExecutionContext ec) {
    this.formFactory = formFactory;
    this.placeForm = formFactory.form(PlaceForm.class);
    this.placeRepository = placeRepository;
    this.ec = ec;
  }

  public CompletionStage<Result> index() {
    return placeRepository
        .list()
        .thenApplyAsync(
            places -> {
              return ok(views.html.places.index.render(places));
            },
            ec.current());
  }

  public Result add() {
    return ok(views.html.places.add.render(placeForm));
  }

  public CompletionStage<Result> create() {
    Form form = formFactory.form(Place.class).bindFromRequest();

    try {
      Place place = (Place) form.get();
      return placeRepository
          .add(place)
          .thenApplyAsync(
              p -> {
                return redirect(routes.PlacesController.index());
              },
              ec.current());
    } catch (IllegalStateException e) {
      return CompletableFuture.completedFuture(ok(views.html.places.add.render(form)));
    }
  }

  public CompletionStage<Result> show(int id) {
    return placeRepository
        .get(id)
        .thenApplyAsync(
            place -> {
              return ok(views.html.places.show.render(place, id));
            },
            ec.current());
  }

  public CompletionStage<Result> edit(int id) {
    return placeRepository
        .get(id)
        .thenApplyAsync(
            place -> {
              PlaceForm form = new PlaceForm(id);
              form.setNo(place.no);
              form.setName(place.name);
              form.setLatlng(place.latlng);

              Form<PlaceForm> formdata = placeForm.fill(form);
              return ok(views.html.places.edit.render(formdata, id));
            },
            ec.current());
  }

  public CompletionStage<Result> update(int id) {
    Form form = formFactory.form(Place.class).bindFromRequest();

    try {
      Place place = (Place) form.get();
      place.id = id;

      return placeRepository
          .update(place)
          .thenApplyAsync(
              p -> {
                return redirect(routes.PlacesController.index());
              },
              ec.current());
    } catch (IllegalStateException e) {
      return CompletableFuture.completedFuture(ok(views.html.places.edit.render(form, id)));
    }
  }

  public CompletionStage<Result> remove(int id) {
    return placeRepository
        .delete(id)
        .thenApplyAsync(
            p -> {
              return redirect(routes.PlacesController.index());
            },
            ec.current());
  }
}
