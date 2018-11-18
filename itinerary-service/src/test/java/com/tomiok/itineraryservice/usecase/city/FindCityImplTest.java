package com.tomiok.itineraryservice.usecase.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.usecase.gateways.CityEntityGateway;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindCityImplTest {

  @Mock
  private CityEntityGateway cityEntityGateway;

  @InjectMocks
  private FindCityImpl findCity;

  @Test
  public void findAll() {
    City bcn = new CityMother().withName("Barcelona").withCode("BCN").build();
    City tokio = new CityMother().withName("Tokio").withCode("TKN").build();
    when(cityEntityGateway.findAll()).thenReturn(Arrays.asList(bcn, tokio));

    List<City> cities = findCity.findAll();

    assertThat(cities).hasSize(2);
    assertThat(cities.stream().anyMatch(city -> city.getName().equals(bcn.getName()))).isTrue();
    assertThat(cities.stream().anyMatch(city -> city.getCode().equals(bcn.getCode()))).isTrue();
    assertThat(cities.stream().anyMatch(city -> city.getName().equals(tokio.getName()))).isTrue();
    assertThat(cities.stream().anyMatch(city -> city.getCode().equals(tokio.getCode()))).isTrue();

    verify(cityEntityGateway, times(1)).findAll();
  }
}
