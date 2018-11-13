package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.model.City;
import lombok.Getter;
import lombok.Setter;

public interface CreateCity {

  City createCity(CreateCityCmd cmd);

  @Getter
  @Setter
  class CitySummary {

    private String name;
    private String code;

    public CitySummary(City city) {
      this.name = city.getName();
      this.code = city.getCode();
    }
  }

  @Getter
  @Setter
  class CreateCityCmd {

    private String name;
    private String code;
  }
}
