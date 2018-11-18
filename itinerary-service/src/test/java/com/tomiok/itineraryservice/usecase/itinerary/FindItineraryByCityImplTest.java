package com.tomiok.itineraryservice.usecase.itinerary;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.gateways.ItineraryEntityGateway;
import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindItineraryByCityImplTest {

  @Mock
  private ItineraryEntityGateway itineraryEntityGateway;

  @InjectMocks
  private FindItineraryByCityImpl findItineraryByCity;

  @Test
  public void findItinerariesByCity() {

    String cityCriteria = "ROS";
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime plusSomeHours = now.plusHours(4);
    int stops = 4;

    City rosario = new City("Rosario", "ROS");
    City mdq = new City("Mar del Plata", "MDQ");

    Itinerary itinerary = new Itinerary(rosario, mdq, now, plusSomeHours, stops);
    when(itineraryEntityGateway.findByCity(cityCriteria)).thenReturn(singletonList(itinerary));

    List<Itinerary> actual = findItineraryByCity.findItinerariesByCity(cityCriteria);

    assertThat(actual).hasSize(1);
    assertThat(actual.stream().anyMatch(it -> it.getDepartureCity().getName().equals("Rosario"))).isTrue();
    assertThat(actual.stream().anyMatch(it -> it.getDestinyCity().getName().equals("Mar del Plata"))).isTrue();
    assertThat(actual.stream().anyMatch(it -> it.getNumOfStops() == 4)).isTrue();

    verify(itineraryEntityGateway, times(1)).findByCity(cityCriteria);
  }
}
