package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.gateways.CityEntityGateway;
import com.tomiok.itineraryservice.usecase.gateways.CityNotFoundException;
import com.tomiok.itineraryservice.usecase.gateways.ItineraryEntityGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CreateItineraryImpl implements CreateItinerary {

  private static Logger log = LoggerFactory.getLogger(CreateItineraryImpl.class);

  private final ItineraryEntityGateway itineraryEntityGateway;

  private final CityEntityGateway cityEntityGateway;

  CreateItineraryImpl(final ItineraryEntityGateway itineraryEntityGateway,
                      final CityEntityGateway cityEntityGateway) {
    this.itineraryEntityGateway = itineraryEntityGateway;
    this.cityEntityGateway = cityEntityGateway;
  }

  @Override
  public Itinerary create(final CreateItineraryCmd cmd) {
    validateCityInDatabase(cmd.getDepartureCity());
    validateCityInDatabase(cmd.getDestinyCity());

    return itineraryEntityGateway
        .create(
            cmd.getDepartureCity(),
            cmd.getDestinyCity(),
            cmd.getDepartureTime(),
            cmd.getArrivalTime(),
            cmd.getNumOfStops());

  }

  private void validateCityInDatabase(String city) {
    if (!cityEntityGateway.existsWithNameOrCode(city)) {
      log.warn("The city identifier {} is not in the database.", city);
      throw new CityNotFoundException("Invalid city name or code");
    }
  }
}
