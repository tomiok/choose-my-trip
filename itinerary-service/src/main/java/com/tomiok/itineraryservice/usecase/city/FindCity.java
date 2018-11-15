package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.model.City;
import java.util.List;

/**
 * Find a cities (or all of them) given a criteria.
 */
public interface FindCity {

  /**
   * Get all the cities in the database.
   *
   * @return A list of cities.
   */
  List<City> findAll();
}
