package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.Itinerary;
import java.time.LocalDateTime;
import java.util.List;

public interface ItineraryEntityGateway {

  List<Itinerary> findByCity(String cityNameOrCode);

  Itinerary create(
      String departureCity,
      String destinyCity,
      LocalDateTime departureTime,
      LocalDateTime arrivalTime,
      int numOfStops);
}
