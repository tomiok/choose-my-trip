package com.tomiok.tripselectorservice.clients.http.itineraries;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItineraryResponse {

  private String departureCityName;
  private String departureCityCode;
  private String destinyCityName;
  private String destinyCityCode;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private int numOfStops;
}
