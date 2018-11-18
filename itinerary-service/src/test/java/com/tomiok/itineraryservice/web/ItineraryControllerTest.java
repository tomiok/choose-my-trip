package com.tomiok.itineraryservice.web;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tomiok.itineraryservice.adapters.itinerary.ItineraryService;
import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ItineraryController.class)
public class ItineraryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ItineraryService itineraryService;

  @Test
  public void findByCity() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime plusSomeHours = now.plusHours(4);
    int stops = 4;

    City rosario = new City("Rosario", "ROS");
    City mdq = new City("Mar del Plata", "MDQ");

    Itinerary itinerary = new Itinerary(rosario, mdq, now, plusSomeHours, stops);
    itinerary.setId(1L);

    List<CreateItinerary.ItinerarySummary> summaries =
        Stream.of(itinerary)
            .map(CreateItinerary.ItinerarySummary::new)
            .collect(toList());

    when(itineraryService.findByCity("ROS"))
        .thenReturn(summaries);

    mockMvc.perform(get("/itineraries/{city}", "ROS"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id", is(1)))
        .andExpect(jsonPath("$.[0].departureCityName", is("Rosario")))
        .andExpect(jsonPath("$.[0].destinyCityCode", is("MDQ")))
        .andExpect(jsonPath("$.[0].numOfStops", is(4)));

  }

  @Test
  public void shouldFailGivenEmptyParam() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime plusSomeHours = now.plusHours(4);
    int stops = 4;

    City rosario = new City("Rosario", "ROS");
    City mdq = new City("Mar del Plata", "MDQ");

    Itinerary itinerary = new Itinerary(rosario, mdq, now, plusSomeHours, stops);
    itinerary.setId(1L);

    List<CreateItinerary.ItinerarySummary> summaries =
        Stream.of(itinerary)
            .map(CreateItinerary.ItinerarySummary::new)
            .collect(toList());

    when(itineraryService.findByCity("ROS"))
        .thenReturn(summaries);

    mockMvc.perform(get("/itineraries/{city}", StringUtils.EMPTY + " "))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("City identifier should not be blank or empty")));
  }
}
