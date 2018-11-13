package com.tomiok.itineraryservice.usecase.city;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.usecase.gateways.CityEntityGatewayImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateCityImplTest {

  @Mock
  private CityEntityGatewayImpl entityGateway;

  @InjectMocks
  private CreateCityImpl createCity;

  @Test
  public void createCity() {
    String cityName = "New York City";
    String cityCode = "NYC";
    when(entityGateway.create(cityName, cityCode)).thenReturn(new City(cityName, cityCode));
    City actual = createCity.createCity(new CreateCity.CreateCityCmd(cityName, cityCode));
    assertThat(actual.getCode()).isEqualTo(cityCode);
    assertThat(actual.getName()).isEqualTo(cityName);
  }
}
