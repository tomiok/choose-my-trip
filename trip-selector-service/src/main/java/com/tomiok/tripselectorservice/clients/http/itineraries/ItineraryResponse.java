package com.tomiok.tripselectorservice.clients.http.itineraries;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryResponse {

  private long id;
  private String departureCityName;
  private String departureCityCode;
  private String destinyCityName;
  private String destinyCityCode;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private int numOfStops;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ItineraryResponse{");
    sb.append("id=").append(id);
    sb.append(", departureCityName='").append(departureCityName).append('\'');
    sb.append(", departureCityCode='").append(departureCityCode).append('\'');
    sb.append(", destinyCityName='").append(destinyCityName).append('\'');
    sb.append(", destinyCityCode='").append(destinyCityCode).append('\'');
    sb.append(", departureTime=").append(departureTime);
    sb.append(", arrivalTime=").append(arrivalTime);
    sb.append(", numOfStops=").append(numOfStops);
    sb.append('}');
    return sb.toString();
  }
}
