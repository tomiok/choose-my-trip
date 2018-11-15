package com.tomiok.itineraryservice.adapters.itinerary;

import static java.util.stream.Collectors.toList;

import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import com.tomiok.itineraryservice.usecase.itinerary.FindItineraryByCity;
import java.util.List;

public class ItineraryServiceImpl implements ItineraryService {

  private final CreateItinerary createItinerary;

  private final FindItineraryByCity findItineraryByCity;

  ItineraryServiceImpl(final CreateItinerary createItinerary,
                       final FindItineraryByCity findItineraryByCity) {
    this.createItinerary = createItinerary;
    this.findItineraryByCity = findItineraryByCity;
  }

  @Override
  public CreateItinerary.ItinerarySummary createItinerary(final CreateItinerary.CreateItineraryCmd cmd) {
    return new CreateItinerary.ItinerarySummary(createItinerary.create(cmd));
  }

  @Override
  public List<CreateItinerary.ItinerarySummary> findByCity(final String cityNameOrCode) {
    return findItineraryByCity
        .findItinerariesByCity(cityNameOrCode)
        .stream()
        .map(CreateItinerary.ItinerarySummary::new)
        .collect(toList());
  }
}
