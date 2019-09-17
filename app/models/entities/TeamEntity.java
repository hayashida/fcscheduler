package models.entities;

import javax.persistence.*;
import lombok.Data;
import play.data.validation.Constraints.*;

@Entity
@Data
@Table(name = "teams")
public class TeamEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Required private Integer no;

  @Required private String name;

  public TeamEntity() {
    super();
  }

  public TeamEntity(int id, int no, String name) {
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
