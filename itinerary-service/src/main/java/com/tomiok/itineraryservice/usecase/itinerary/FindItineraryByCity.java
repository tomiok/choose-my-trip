package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.model.Itinerary;
import java.util.List;

/**
 * This use case is to find all the itineraries given a departure city identifier (could be either the name or the
 * code)
 */
public interface FindItineraryByCity {

  /**
   * A list of itineraries
   * @param cityNameOrCode The city name or code.
   * @return A list of itineraries.
   */
  List<Itinerary> findItinerariesByCity(String cityNameOrCode);
}
