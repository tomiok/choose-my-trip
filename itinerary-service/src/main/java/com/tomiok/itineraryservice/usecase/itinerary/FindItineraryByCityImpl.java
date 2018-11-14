package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.gateways.ItineraryEntityGateway;
import java.util.List;

class FindItineraryByCityImpl implements FindItineraryByCity {

  private final ItineraryEntityGateway itineraryEntityGateway;

  FindItineraryByCityImpl(
      final ItineraryEntityGateway itineraryEntityGateway) {
    this.itineraryEntityGateway = itineraryEntityGateway;
  }

  @Override
  public List<Itinerary> findItinerariesByCity(final String cityNameOrCode) {
    return itineraryEntityGateway.findByCity(cityNameOrCode);
  }
}
