package forms;

import lombok.Data;

@Data
public class TeamForm {
  protected int id;
  protected int no;
  protected String name;

  public TeamForm() {
    super();
  }

  public TeamForm(int id) {
    super();
    this.id = id;
  }
}
