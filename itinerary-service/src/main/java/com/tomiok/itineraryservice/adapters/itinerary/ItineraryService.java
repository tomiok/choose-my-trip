package com.tomiok.itineraryservice.adapters.itinerary;

import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import java.util.List;

public interface ItineraryService {

  CreateItinerary.ItinerarySummary createItinerary(CreateItinerary.CreateItineraryCmd cmd);

  List<CreateItinerary.ItinerarySummary> findByCity(String cityNameOrCode);
}
