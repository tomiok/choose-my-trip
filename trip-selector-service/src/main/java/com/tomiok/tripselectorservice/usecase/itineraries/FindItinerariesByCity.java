package com.tomiok.tripselectorservice.usecase.itineraries;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import java.util.List;
import java.util.Map;

/**
 * Fetch the itineraries given a departure city in the service <b>Itinerary-service</b>
 */
public interface FindItinerariesByCity {

  /**
   * @param cityIdentifier Either the city name or code
   *
   * @return A Map with the key explaining which criteria is used, STOPS or TIME.
   *     STOPS is ordered ASC for the number of stops that the itinerary contains.
   *     TIME is ordered ASC for the time that the trip takes.
   *
   * @see SortedType
   * @see ItineraryResponse
   */
  Map<SortedType, List<ItineraryResponse>> fetchAndOrderItineraries(String cityIdentifier);
}
