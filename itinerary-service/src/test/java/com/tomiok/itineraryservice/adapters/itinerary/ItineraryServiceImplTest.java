package com.tomiok.itineraryservice.adapters.itinerary;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import com.tomiok.itineraryservice.usecase.itinerary.FindItineraryByCity;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryServiceImplTest {

  @Mock
  private CreateItinerary createItinerary;

  @Mock
  private FindItineraryByCity findItineraryByCity;

  @InjectMocks
  private ItineraryServiceImpl itineraryService;

  @Test
  public void findByCity() {

    String cityCriteria = "ROS";

    Mockito.when(findItineraryByCity.findItinerariesByCity(cityCriteria))
        .thenReturn(asList(createIt_1(), createIt_2()));

    List<CreateItinerary.ItinerarySummary> actual = itineraryService.findByCity(cityCriteria);

    assertThat(actual).hasSize(2);
    assertThat(actual.stream().anyMatch(s -> s.getNumOfStops() == 4)).isTrue();
    assertThat(actual.stream().anyMatch(s -> s.getNumOfStops() == 7)).isTrue();

    verify(findItineraryByCity, times(1)).findItinerariesByCity(cityCriteria);
    verifyZeroInteractions(createItinerary);
  }

  private Itinerary createIt_1() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime plusSomeHours = now.plusHours(4);
    int stops = 4;

    City rosario = new City("Rosario", "ROS");
    City mdq = new City("Mar del Plata", "MDQ");

    Itinerary itinerary = new Itinerary(rosario, mdq, now, plusSomeHours, stops);
    itinerary.setId(1L);
    return itinerary;
  }

  private Itinerary createIt_2() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime plusSomeHours = now.plusHours(9).plusMinutes(45);
    int stops = 7;

    City rosario = new City("Rosario", "ROS");
    City mdq = new City("Mar del Plata", "MDQ");

    Itinerary itinerary = new Itinerary(rosario, mdq, now, plusSomeHours, stops);
    itinerary.setId(2L);
    return itinerary;
  }
}
