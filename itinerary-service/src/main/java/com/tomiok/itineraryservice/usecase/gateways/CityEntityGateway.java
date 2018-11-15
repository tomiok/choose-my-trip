package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.City;
import java.util.List;

/**
 * An interface with all the necessary methods to interact with the {@link City} class. All the methods performs
 * database operations.
 * This interface provides an API for the clients who want to retrieve, delete, update or create a city.
 */
public interface CityEntityGateway {

  /**
   * Creates a city.
   *
   * @param name The city name.
   * @param code The city code.
   *
   * @return The city created and saved in the database.
   */
  City create(String name, String code);

  /**
   * Delete by id.
   *
   * @param id The city id that want to delete.
   */
  void delete(long id);

  /**
   * Delete by code
   *
   * @param code The city code that want to delete.
   */
  void delete(String code);

  /**
   * Get all the cities in the database.
   *
   * @return A list of the cities in the database.
   */
  List<City> findAll();

  /**
   * Check if a city exists in the database.
   *
   * @param nameOrCode The city name or code.
   *
   * @return True if is in the database already, otherwise false.
   *
   * @see City
   */
  boolean existsWithNameOrCode(String nameOrCode);

  /**
   * Find a city by name or code.
   *
   * @param nameOrCode The city name or code.
   *
   * @return The city founded.
   */
  City findByNameOrCode(String nameOrCode);
}
