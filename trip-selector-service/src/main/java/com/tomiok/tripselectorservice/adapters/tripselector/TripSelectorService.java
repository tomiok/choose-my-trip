package com.tomiok.tripselectorservice.adapters.tripselector;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import com.tomiok.tripselectorservice.usecase.itineraries.SortedType;
import java.util.List;
import java.util.Map;

/**
 * This class agglomerates the use cases for the trips/itineraries that are fetch from the <b>itinerary-service</b>
 */
public interface TripSelectorService {

  /**
   * @param cityIdentifier Either the city name or code.
   *
   * @return A Map which the key is the type or sorting and the value is the list of itineraries.
   */
  Map<SortedType, List<ItineraryResponse>> fetchItinerariesByCity(String cityIdentifier);
}
