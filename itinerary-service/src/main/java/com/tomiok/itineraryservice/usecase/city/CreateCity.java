package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Creates a city given the name and the code.
 */
public interface CreateCity {

  /**
   * Creates a new City
   *
   * @param cmd The create command.
   *
   * @return The city created.
   *
   * @see CreateCityCmd
   */
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
  @AllArgsConstructor
  class CreateCityCmd {

    private String name;
    private String code;
  }
}
