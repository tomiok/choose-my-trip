package com.tomiok.itineraryservice.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String code;

  public City() {}

  public City(final String name, final String code) {
    this.name = name;
    this.code = code;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    City city = (City) o;
    return Objects.equals(id, city.id)
           && Objects.equals(name, city.name)
           && Objects.equals(code, city.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
