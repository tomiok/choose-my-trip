package com.tomiok.itineraryservice.adapters.itinerary;

import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import java.util.List;

/**
 * This class agglomerates the uses cases for {@link com.tomiok.itineraryservice.model.Itinerary} entity class.
 */
public interface ItineraryService {

  /**
   * Create an itinerary given the create command.
   *
   * @param cmd The create command class.
   *
   * @return The itinerary created.
   *
   * @see com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary.CreateItineraryCmd
   * @see com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary.ItinerarySummary
   */
  CreateItinerary.ItinerarySummary createItinerary(CreateItinerary.CreateItineraryCmd cmd);

  /**
   * Find all the itineraries given a departure city.
   *
   * @param cityNameOrCode The identification for the city, could be either the city name or the city code.
   *                       i.e. for New York City, the code should be 'NYC'.
   *
   * @return A list with all the itineraries.
   *
   * @see com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary.ItinerarySummary
   */
  List<CreateItinerary.ItinerarySummary> findByCity(String cityNameOrCode);
}
