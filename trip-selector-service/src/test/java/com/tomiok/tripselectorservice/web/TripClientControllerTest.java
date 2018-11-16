package com.tomiok.tripselectorservice.web;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tomiok.tripselectorservice.adapters.tripselector.TripSelectorService;
import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import com.tomiok.tripselectorservice.usecase.itineraries.SortedType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TripClientController.class)
public class TripClientControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TripSelectorService tripSelectorService;

  private Map<SortedType, List<ItineraryResponse>> resByType;

  @Before
  public void setUp() {
    resByType = new HashMap<>(2);
  }

  @Test
  public void findItinerariesByCities() throws Exception {
    String city = "ROS";
    LocalDateTime now = LocalDateTime.now();

    // Three simple itineraries, with 0,1 and 2 stops.
    // The 1 stops is longer than the 2 stops
    ItineraryResponse ros_bsas_0stops = mockIt(now, now.plusDays(1), "Rosario", "ROS", "Buenos Aires", "BSAS", 0);
    ItineraryResponse ros_bsas_1stop = mockIt(now, now.plusDays(2), "Rosario", "ROS", "Buenos Aires", "BSAS", 1);
    ItineraryResponse ros_bsas_2stop = mockIt(now, now.plusHours(16), "Rosario", "ROS", "Buenos Aires", "BSAS", 2);

    createMockResponse(SortedType.BY_STOPS, asList(ros_bsas_0stops, ros_bsas_1stop, ros_bsas_2stop));
    createMockResponse(SortedType.BY_TIME, asList(ros_bsas_0stops, ros_bsas_2stop, ros_bsas_1stop));

    when(tripSelectorService.fetchItinerariesByCity(city)).thenReturn(resByType);

    mockMvc.perform(get("/itineraries-finder/{city}", "ROS")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.BY_TIME.[0].departureCityName", is("Rosario")))
        .andExpect(jsonPath("$.BY_TIME.[0].numOfStops", is(0)))
        .andExpect(jsonPath("$.BY_TIME.[0].arrivalTime", is(now.plusDays(1).toString())))

        .andExpect(jsonPath("$.BY_TIME.[1].departureCityName", is("Rosario")))
        .andExpect(jsonPath("$.BY_TIME.[1].numOfStops", is(2)))
        .andExpect(jsonPath("$.BY_TIME.[1].arrivalTime", is(now.plusHours(16).toString())))

        .andExpect(jsonPath("$.BY_TIME.[2].departureCityName", is("Rosario")))
        .andExpect(jsonPath("$.BY_TIME.[2].numOfStops", is(1)))
        .andExpect(jsonPath("$.BY_TIME.[2].arrivalTime", is(now.plusDays(2).toString())))

        .andExpect(jsonPath("$.BY_STOPS.[0].destinyCityName", is("Buenos Aires")))
        .andExpect(jsonPath("$.BY_STOPS.[0].numOfStops", is(0)))
        .andExpect(jsonPath("$.BY_STOPS.[0].departureTime", is(now.toString())))

        .andExpect(jsonPath("$.BY_STOPS.[1].destinyCityName", is("Buenos Aires")))
        .andExpect(jsonPath("$.BY_STOPS.[1].numOfStops", is(1)))
        .andExpect(jsonPath("$.BY_STOPS.[1].departureTime", is(now.toString())))

        .andExpect(jsonPath("$.BY_STOPS.[2].destinyCityName", is("Buenos Aires")))
        .andExpect(jsonPath("$.BY_STOPS.[2].numOfStops", is(2)))
        .andExpect(jsonPath("$.BY_STOPS.[2].departureTime", is(now.toString())));
  }

  @Test
  public void shouldReturnEmptyMap_GivenUnregisteredCity() throws Exception {
    when(tripSelectorService.fetchItinerariesByCity("unknown")).thenReturn(emptyMap());
    mockMvc
        .perform(get("/itineraries-finder/{city}", "ROS"))
        .andExpect(status().isOk());
  }

  private void createMockResponse(SortedType type, List<ItineraryResponse> res) {
    resByType.merge(type, res, (l1, l2) -> Stream.of(l1, l2).flatMap(Collection::stream).collect(toList()));
  }

  private ItineraryResponse mockIt(
      LocalDateTime departureTime,
      LocalDateTime arrivalTime,
      String departureCityName,
      String departureCityCode,
      String arrivalCityName,
      String arrivalCityCode,
      int numOfStops
  ) {
    return new ItineraryResponse(departureCityName, departureCityCode, arrivalCityName, arrivalCityCode, departureTime,
        arrivalTime, numOfStops);
  }
}
