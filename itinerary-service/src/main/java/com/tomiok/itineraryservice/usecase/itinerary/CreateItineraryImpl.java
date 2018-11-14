package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.gateways.ItineraryEntityGateway;

class CreateItineraryImpl implements CreateItinerary {

  private final ItineraryEntityGateway itineraryEntityGateway;

  CreateItineraryImpl(final ItineraryEntityGateway itineraryEntityGateway) {
    this.itineraryEntityGateway = itineraryEntityGateway;
  }

  @Override
  public Itinerary create(final CreateItineraryCmd cmd) {
    return itineraryEntityGateway
        .create(cmd.getDepartureCity(), cmd.getDestinyCity(), cmd.getDepartureTime(),
            cmd.getArrivalTime(), cmd.getNumOfStops());
  }
}
