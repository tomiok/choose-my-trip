package com.tomiok.itineraryservice.usecase.gateways;

import static org.assertj.core.api.Assertions.assertThat;

import com.tomiok.itineraryservice.model.Itinerary;
import com.tomiok.itineraryservice.model.ItineraryRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItineraryEntityGatewayImplTest {

  @Autowired
  private ItineraryEntityGateway itineraryEntityGateway;

  @Autowired
  private ItineraryRepository itineraryRepository;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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
    thrown.expect(EmptyItinerariesException.class);
    itineraryEntityGateway.findByCity("Unknown city");
  }

  @Test
  public void create() {
    Itinerary itinerary =
        itineraryEntityGateway.create("ROS", "NYC", LocalDateTime.now(), LocalDateTime.now().plusDays(2), 4);

    assertThat(itinerary.getDepartureCity().getName()).isEqualTo("Rosario");
    assertThat(itinerary.getDepartureCity().getCode()).isEqualTo("ROS");

    assertThat(itinerary.getDestinyCity().getName()).isEqualTo("New York City");
    assertThat(itinerary.getDestinyCity().getCode()).isEqualTo("NYC");

    assertThat(itinerary.getNumOfStops()).isEqualTo(4);

    // cleanup
    itineraryRepository.delete(itinerary);
  }
}
