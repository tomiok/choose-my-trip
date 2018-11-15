package com.tomiok.itineraryservice.usecase.gateways;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomiok.itineraryservice.model.Itinerary;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItineraryEntityGatewayImplTest {

  @Autowired
  private ItineraryEntityGateway itineraryEntityGateway;

  @Test
  public void findByCityWithCode() {
    List<Itinerary> itineraries = itineraryEntityGateway.findByCity("ROS");
    assertThat(itineraries).hasSize(3);
  }

  @Test
  public void findByCityWithName() {
    List<Itinerary> itineraries = itineraryEntityGateway.findByCity("Rosario");
    assertThat(itineraries).hasSize(3);
  }

  @Test
  public void shouldReturnEmptyList_GivenUnknownCity() {
    List<Itinerary> itineraries = itineraryEntityGateway.findByCity("Unknown city");
    assertThat(itineraries).isEmpty();
  }

  @Test
  public void create() {
    itineraryEntityGateway.create("ROS", "NYC", LocalDateTime.now(), LocalDateTime.now().plusDays(2), 4);
  }
}
