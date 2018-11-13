package com.tomiok.itineraryservice.usecase.gateways;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.rules.ExpectedException.none;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.CityRepository;
import java.util.List;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityEntityGatewayImplTest {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CityEntityGateway cityEntityGateway;

  @Rule
  public ExpectedException thrown = none();

  @After
  public void cleanUp() {
    cityRepository.deleteAll();
  }

  @Test
  public void create() {
    City city = cityEntityGateway.create("New York City", "NYC");
    assertThat(city.getId()).isPositive();
    assertThat(city.getName()).isEqualTo("New York City");
    assertThat(city.getCode()).isEqualTo("NYC");
  }

  @Test
  public void findAll() {
    City nyc = cityEntityGateway.create("New York City", "NYC");
    City bsAs = cityEntityGateway.create("Buenos Aires", "BSAS");

    List<City> cities = cityEntityGateway.findAll();

    assertThat(cities).hasSize(2);
    assertThat(cities.stream().anyMatch(r -> r.getCode().equals(nyc.getCode()))).isTrue();
    assertThat(cities.stream().anyMatch(r -> r.getCode().equals(bsAs.getCode()))).isTrue();
    assertThat(cities.stream().anyMatch(r -> r.getName().equals("Madrid"))).isFalse();
  }

  @Test
  public void existsWithNameOrCode() {
    cityEntityGateway.create("Rosario", "ROS");

    boolean existsWithName = cityEntityGateway.existsWithNameOrCode("Rosario");
    assertThat(existsWithName).isTrue();

    boolean existsWithCode = cityEntityGateway.existsWithNameOrCode("ROS");
    assertThat(existsWithCode).isTrue();
  }

  @Test
  public void shouldReturnFalse_GivenWrongNameOrCode() {
    cityEntityGateway.create("Zaragoza", "ZAZ");

    boolean existsWithName = cityEntityGateway.existsWithNameOrCode("Rosario");
    assertThat(existsWithName).isFalse();

    boolean existsWithCode = cityEntityGateway.existsWithNameOrCode("ROS");
    assertThat(existsWithCode).isFalse();
  }

  @Test
  public void shouldFail_GivenNullName() {
    thrown.expect(NullPointerException.class);
    cityEntityGateway.create(null, "ZAZ");
  }

  @Test
  public void shouldFail_GivenNullCode() {
    thrown.expect(NullPointerException.class);
    cityEntityGateway.create("Zaragoza", null);
  }
}
