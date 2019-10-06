package models;

import javax.persistence.*;
import lombok.Data;
import play.data.validation.Constraints.*;

@Entity
@Data
@Table(name = "places")
public class Place extends AppModel {

  @Required public Integer no;
  @Required public String name;
  public String latlng;

  public Place() {
    super();
  }

  public Place(int id, int no, String name, String latlng) {
    super();

    this.id = id;
    this.no = no;
    this.name = name;
    this.latlng = latlng;
  }

  @Override
  public String toString() {
    return String.format("[%d] %s", no, name);
  }
}
