package forms;

import lombok.Data;

@Data
public class PlaceForm {
  protected int id;
  protected int no;
  protected String name;
  protected String Latlng;

  public PlaceForm() {
    super();
  }

  public PlaceForm(int id) {
    super();
    this.id = id;
  }
}
