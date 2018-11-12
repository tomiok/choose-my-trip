package com.tomiok.itineraryservice.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Trip {

  @Id
  @GeneratedValue
  private Long id;

  private City initialCity;

  private City endCity;

  private LocalDateTime departureTime;

  private LocalDateTime arrivalTime;

  private int numOfStops;

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Trip trip = (Trip) o;
    return numOfStops == trip.numOfStops &&
           Objects.equals(id, trip.id) &&
           Objects.equals(initialCity, trip.initialCity) &&
           Objects.equals(endCity, trip.endCity) &&
           Objects.equals(departureTime, trip.departureTime) &&
           Objects.equals(arrivalTime, trip.arrivalTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, initialCity, endCity, departureTime, arrivalTime, numOfStops);
  }
}
