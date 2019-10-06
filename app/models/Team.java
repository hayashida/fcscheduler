package models;

import javax.persistence.*;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Data;
import play.data.validation.Constraints.*;

@Entity
@Data
@Table(name = "teams")
@NamedQueries({
  @NamedQuery(name = "Team.findAll", query = "select p from Team p order by p.no asc"),
  @NamedQuery(name = "Team.findById", query = "select p from Team p where p.id = :id"),
})
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
