package com.tomiok.itineraryservice.adapters.city;

import com.tomiok.itineraryservice.usecase.city.CreateCity;
import java.util.List;

/**
 * This class agglomerates all the use cases for {@link com.tomiok.itineraryservice.model.City} entity class.
 */
public interface CityService {

  /**
   * Creates a new city given the create command.
   *
   * @param cmd The command to create the city.
   *
   * @return The city created.
   *
   * @see com.tomiok.itineraryservice.usecase.city.CreateCity.CitySummary
   * @see com.tomiok.itineraryservice.usecase.city.CreateCity.CreateCityCmd
   */
  CreateCity.CitySummary createCity(CreateCity.CreateCityCmd cmd);

  /**
   * Get all the cities.
   *
   * @return a List of cities.
   *
   * @see com.tomiok.itineraryservice.usecase.city.CreateCity.CitySummary
   */
  List<CreateCity.CitySummary> findAll();
}
