package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.usecase.gateways.CityEntityGateway;
import java.util.List;

class FindCityImpl implements FindCity {

  private final CityEntityGateway cityEntityGateway;

  FindCityImpl(final CityEntityGateway cityEntityGateway) {
    this.cityEntityGateway = cityEntityGateway;
  }

  @Override
  public List<City> findAll() {
    return cityEntityGateway.findAll();
  }
}
