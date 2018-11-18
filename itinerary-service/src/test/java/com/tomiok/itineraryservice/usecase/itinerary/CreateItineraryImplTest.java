package com.tomiok.itineraryservice.usecase.itinerary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.gateways.CityEntityGateway;
import com.tomiok.itineraryservice.usecase.gateways.CityNotFoundException;
import com.tomiok.itineraryservice.usecase.gateways.ItineraryEntityGateway;
import java.time.LocalDateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateItineraryImplTest {

  @Mock
  private ItineraryEntityGateway itineraryEntityGateway;

  @Mock
  private CityEntityGateway cityEntityGateway;

  @InjectMocks
  private CreateItineraryImpl createItinerary;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void create() {

    String departureCityCode = "ROS";
    String destinyCityCode = "CBA";

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after1day = now.plusDays(1);
    int stops = 3;

    City rosario = new City("Rosario", departureCityCode);
    City cordoba = new City("Cordoba", destinyCityCode);

    when(cityEntityGateway.existsWithNameOrCode(departureCityCode)).thenReturn(true);
    when(cityEntityGateway.existsWithNameOrCode(destinyCityCode)).thenReturn(true);

    Itinerary itinerary = new Itinerary(rosario, cordoba, now, after1day, stops);
    when(itineraryEntityGateway.create(departureCityCode, destinyCityCode, now, after1day, stops))
        .thenReturn(itinerary);

    Itinerary actual = createItinerary.create(
        new CreateItinerary.CreateItineraryCmd(departureCityCode, destinyCityCode, now, after1day, stops));

    assertThat(actual.getDepartureCity()).isEqualTo(rosario);
    assertThat(actual.getDestinyCity()).isEqualTo(cordoba);
    assertThat(actual.getDepartureTime()).isEqualTo(now);
    assertThat(actual.getArrivalTime()).isEqualTo(after1day);

    verify(cityEntityGateway, times(2)).existsWithNameOrCode(anyString());
    verify(itineraryEntityGateway, times(1))
        .create("ROS", "CBA", now, after1day, stops);
  }

  @Test
  public void shouldFail_GivenNotSavedCity() {
    thrown.expect(CityNotFoundException.class);

    String departureCityCode = "ROS";
    String destinyCityCode = "CBA";

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after1day = now.plusDays(1);
    int stops = 3;

    when(cityEntityGateway.existsWithNameOrCode(departureCityCode)).thenReturn(false);

    createItinerary
        .create(new CreateItinerary.CreateItineraryCmd(departureCityCode, destinyCityCode, now, after1day, stops));

    verify(cityEntityGateway, times(2)).existsWithNameOrCode(anyString());
    verifyZeroInteractions(itineraryEntityGateway);
  }
}
