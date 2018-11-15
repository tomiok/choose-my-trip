package com.tomiok.itineraryservice.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

/**
 * Entity class to model an itinerary.
 * With a departure city, destiny city, departure and arrival time and the number of stops that the trip contains.
 */
@Entity
@Getter
@Setter
public class Itinerary {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departure_city")
  private City departureCity;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destiny_city")
  private City destinyCity;

  private LocalDateTime departureTime;

  private LocalDateTime arrivalTime;

  private int numOfStops;

  public Itinerary() {}

  public Itinerary(final City departureCity, final City destinyCity, final LocalDateTime departureTime,
                   final LocalDateTime arrivalTime,
                   final int numOfStops) {
    Validate.notNull(departureCity, "Departure city cannot be null");
    Validate.notNull(destinyCity, "Destiny city cannot be null");
    Validate.notNull(departureTime, "Departure time cannot be null");
    Validate.notNull(arrivalTime, "Arrival time cannot be null");
    Validate.isTrue(departureTime.isBefore(arrivalTime), "Arrival time should be always after than departure");
    Validate.isTrue(numOfStops >= 0, "The number of stops cannot be negative");

    this.departureCity = departureCity;
    this.destinyCity = destinyCity;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.numOfStops = numOfStops;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Itinerary itinerary = (Itinerary) o;
    return numOfStops == itinerary.numOfStops
           && Objects.equals(id, itinerary.id)
           && Objects.equals(departureCity, itinerary.departureCity)
           && Objects.equals(destinyCity, itinerary.destinyCity)
           && Objects.equals(departureTime, itinerary.departureTime)
           && Objects.equals(arrivalTime, itinerary.arrivalTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, departureCity, destinyCity, departureTime, arrivalTime, numOfStops);
  }
}
