package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.model.ItineraryRepository;
import java.time.LocalDateTime;
import java.util.List;

class ItineraryEntityGatewayImpl implements ItineraryEntityGateway {

  private final ItineraryRepository itineraryRepository;

  private final CityEntityGateway cityEntityGateway;

  ItineraryEntityGatewayImpl(final ItineraryRepository itineraryRepository,
                             final CityEntityGateway cityEntityGateway) {
    this.itineraryRepository = itineraryRepository;
    this.cityEntityGateway = cityEntityGateway;
  }

  @Override
  public List<Itinerary> findByCity(final String cityNameOrCode) {
    List<Itinerary> allByDepartureCity = itineraryRepository.findAllByDepartureCity(cityNameOrCode);
    if (allByDepartureCity.isEmpty()) {
      throw new EmptyItinerariesException("Not itineraries");
    }
    return allByDepartureCity;
  }

  @Override
  public Itinerary create(final String departureCity, final String destinyCity, final LocalDateTime departureTime,
                          final LocalDateTime arrivalTime, final int numOfStops) {
    City departure = cityEntityGateway.findByNameOrCode(departureCity);
    City destiny = cityEntityGateway.findByNameOrCode(destinyCity);

    Itinerary itinerary = new Itinerary(departure, destiny, departureTime, arrivalTime, numOfStops);
    return itineraryRepository.save(itinerary);
  }
}
