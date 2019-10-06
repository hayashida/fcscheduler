package models;

import javax.persistence.*;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Data;
import play.data.validation.Constraints.*;

@Entity
@Data
@Table(name = "places")
@NamedQueries({
  @NamedQuery(name = "Place.findAll", query = "select p from Place p order by p.no asc"),
  @NamedQuery(name = "Place.findById", query = "select p from Place p where p.id = :id"),
})
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
