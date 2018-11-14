package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.model.City;
import lombok.Getter;

@Getter
public class CityMother {

  private String name;

  private String code;

  public CityMother withName(String name) {
    this.name = name;
    return this;
  }

  public CityMother withCode(String code) {
    this.code = code;
    return this;
  }

  public City build() {
    return new City(name, code);
  }
}
