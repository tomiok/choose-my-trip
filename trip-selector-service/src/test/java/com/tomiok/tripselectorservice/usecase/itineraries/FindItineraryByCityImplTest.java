package com.tomiok.tripselectorservice.usecase.itineraries;

import static java.time.LocalDateTime.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryProxy;
import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindItineraryByCityImplTest {

  @Mock
  private ItineraryProxy itineraryProxy;

  @InjectMocks
  private FindItineraryByCityImpl findItineraryByCity;

  @Test
  public void fetchAndOrderItineraries() {
    String depCityName = "Rosario";
    String depCityCode = "ROS";
    String arrivalCityName = "Cordoba";
    String arrivalCityCode = "CBA";

    // By time (ASC) IDs => 1 - 4 - 3 - 2
    // By stops (ASC) IDs => 1 - 2 - 4 - 3

    ItineraryResponse res1 =
        createResponse(1, depCityName, depCityCode, arrivalCityName, arrivalCityCode, parse("2018-01-19T03:40:00"),
            parse("2018-01-20T02:10:00"), 0);

    ItineraryResponse res2 = createResponse(2,
        depCityName, depCityCode, arrivalCityName, arrivalCityCode, parse("2018-01-19T03:40:00"),
        parse("2018-01-21T06:10:00"), 1);

    ItineraryResponse res3 = createResponse(3,
        depCityName, depCityCode, arrivalCityName, arrivalCityCode, parse("2018-01-19T03:40:00"),
        parse("2018-01-21T08:10:00"), 5);

    ItineraryResponse res4 = createResponse(4, depCityName, depCityCode, arrivalCityName, arrivalCityCode,
        parse("2018-01-19T03:40:00"), parse("2018-01-20T07:10:00"), 4);
    when(itineraryProxy.doCall("ROS")).thenReturn(Arrays.asList(res1, res2, res3, res4));

    Map<SortedType, List<ItineraryResponse>> responses = findItineraryByCity.fetchAndOrderItineraries("ROS");

    List<ItineraryResponse> byStops = responses.get(SortedType.BY_STOPS);
    assertThat(byStops.get(0).getId()).isEqualTo(1);
    assertThat(byStops.get(1).getId()).isEqualTo(2);
    assertThat(byStops.get(2).getId()).isEqualTo(4);
    assertThat(byStops.get(3).getId()).isEqualTo(3);

    List<ItineraryResponse> byTime = responses.get(SortedType.BY_TIME);
    assertThat(byTime.get(0).getId()).isEqualTo(1);
    assertThat(byTime.get(1).getId()).isEqualTo(4);
    assertThat(byTime.get(2).getId()).isEqualTo(2);
    assertThat(byTime.get(3).getId()).isEqualTo(3);
  }

  private ItineraryResponse createResponse(
      long id,
      String departureCityName,
      String departureCityCode,
      String destinyCityName,
      String destinyCityCode,
      LocalDateTime departureTime,
      LocalDateTime arrivalTime,
      int numOfStops) {
    return new ItineraryResponse(id, departureCityName, departureCityCode, destinyCityName, destinyCityCode,
        departureTime, arrivalTime, numOfStops);
  }
}
