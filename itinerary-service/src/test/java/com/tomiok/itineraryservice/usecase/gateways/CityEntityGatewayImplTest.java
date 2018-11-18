package com.tomiok.itineraryservice.usecase.gateways;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.rules.ExpectedException.none;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.CityRepository;
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
public class CityEntityGatewayImplTest {

  @Autowired
  private CityEntityGateway cityEntityGateway;

  @Autowired
  private CityRepository cityRepository;

  @Rule
  public ExpectedException thrown = none();

  @Test
  public void create() {
    City city = cityEntityGateway.create("New York City", "NYC");
    assertThat(city.getId()).isPositive();
    assertThat(city.getName()).isEqualTo("New York City");
    assertThat(city.getCode()).isEqualTo("NYC");

    //clean up
    cityRepository.delete(city);
  }

  @Test
  public void findAll() {
    List<City> cities = cityEntityGateway.findAll();

    assertThat(cities).hasSize(2);
    assertThat(cities.stream().anyMatch(r -> r.getCode().equals("NYC"))).isTrue();
    assertThat(cities.stream().anyMatch(r -> r.getCode().equals("ROS"))).isTrue();
    assertThat(cities.stream().anyMatch(r -> r.getName().equals("Madrid"))).isFalse();
  }

  @Test
  public void existsWithNameOrCode() {
    boolean existsWithName = cityEntityGateway.existsWithNameOrCode("Rosario");
    assertThat(existsWithName).isTrue();

    boolean existsWithCode = cityEntityGateway.existsWithNameOrCode("ROS");
    assertThat(existsWithCode).isTrue();
  }

  @Test
  public void shouldReturnFalse_GivenWrongNameOrCode() {
    boolean existsWithName = cityEntityGateway.existsWithNameOrCode("Auckland");
    assertThat(existsWithName).isFalse();

    boolean existsWithCode = cityEntityGateway.existsWithNameOrCode("AUCK");
    assertThat(existsWithCode).isFalse();
  }

  @Test
  public void findByNameOrCode() {
    City ros = cityEntityGateway.findByNameOrCode("ROS");
    assertThat(ros.getName()).isEqualTo("Rosario");
    assertThat(ros.getCode()).isEqualTo("ROS");
  }

  @Test
  public void shouldNotFindAnyCity() {
    thrown.expect(CityNotFoundException.class);
    cityEntityGateway.findByNameOrCode("UNKNOWN__CODE");
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
