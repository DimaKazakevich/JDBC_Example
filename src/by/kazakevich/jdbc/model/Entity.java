package by.kazakevich.jdbc.model;

import java.io.Serializable;

public class Entity implements Serializable {

  protected Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
