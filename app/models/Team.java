package models;

import javax.persistence.*;
import lombok.Data;
import play.data.validation.Constraints.*;

@Entity
@Data
@Table(name = "teams")
public class Team extends AppModel {

  @Required public Integer no;

  @Required public String name;

  public Team() {
    super();
  }

  public Team(int id, int no, String name) {
    super();

    this.id = id;
    this.no = no;
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("[%d] %s", no, name);
  }
}
