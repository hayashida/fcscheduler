package models;

import javax.persistence.*;
import play.data.validation.Constraints.*;

@MappedSuperclass
public class AppModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
}
