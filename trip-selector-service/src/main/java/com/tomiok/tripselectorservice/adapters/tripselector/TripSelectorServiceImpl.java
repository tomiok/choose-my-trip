package com.tomiok.tripselectorservice.adapters.tripselector;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import com.tomiok.tripselectorservice.usecase.itineraries.FindItinerariesByCity;
import com.tomiok.tripselectorservice.usecase.itineraries.SortedType;
import java.util.List;
import java.util.Map;

public class TripSelectorServiceImpl implements TripSelectorService {

  private final FindItinerariesByCity findItinerariesByCity;

  TripSelectorServiceImpl(final FindItinerariesByCity findItinerariesByCity) {
    this.findItinerariesByCity = findItinerariesByCity;
  }

  @Override
  public Map<SortedType, List<ItineraryResponse>> fetchItinerariesByCity(final String cityIdentifier) {
    return findItinerariesByCity.fetchItineraries(cityIdentifier);
  }
}
