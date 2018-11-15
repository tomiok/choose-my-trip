package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.Itinerary;
import java.time.LocalDateTime;
import java.util.List;

/**
 * An interface with all the necessary methods to interact with the {@link Itinerary} class. All the methods performs
 * database operations.
 * This interface provides an API for the clients who want to retrieve, delete, update or create an itinerary.
 */
public interface ItineraryEntityGateway {

  /**
   * Get the list of itineraries available for a city.
   *
   * @param cityNameOrCode The city name or code.
   *
   * @return A List of itineraries.
   *
   * @see com.tomiok.itineraryservice.model.City
   * @see Itinerary
   */
  List<Itinerary> findByCity(String cityNameOrCode);

  /**
   * Creates a new itinerary.
   *
   * @param departureCity The departure city.
   * @param destinyCity   The end of the trip.
   * @param departureTime Time that the trip starts.
   * @param arrivalTime   Time that the trip ends.
   * @param numOfStops    The number of stops, starting from zero.
   *
   * @return An itinerary.
   *
   * @see Itinerary
   * @see com.tomiok.itineraryservice.model.City
   * @see LocalDateTime
   */
  Itinerary create(
      String departureCity,
      String destinyCity,
      LocalDateTime departureTime,
      LocalDateTime arrivalTime,
      int numOfStops);
}
