package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.model.Itinerary;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public interface CreateItinerary {

  Itinerary create(CreateItineraryCmd createItineraryCmd);

  @Getter
  @Setter
  class CreateItineraryCmd {
    private String departureCity;
    private String destinyCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int numOfStops;
  }

  @Getter
  @Setter
  class ItinerarySummary {
    private String departureCityName;
    private String departureCityCode;
    private String destinyCityName;
    private String destinyCityCode;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int numOfStops;

    public ItinerarySummary(final Itinerary itinerary) {
      this.departureCityName = itinerary.getDepartureCity().getName();
      this.departureCityCode = itinerary.getDepartureCity().getCode();
      this.destinyCityName = itinerary.getDestinyCity().getName();
      this.destinyCityCode = itinerary.getDestinyCity().getCode();
      this.departureTime = itinerary.getDepartureTime();
      this.arrivalTime = itinerary.getArrivalTime();
    }
  }
}
