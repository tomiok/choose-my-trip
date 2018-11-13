package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.usecase.gateways.CityEntityGateway;

class CreateCityImpl implements CreateCity {

  private final CityEntityGateway cityEntityGateway;

  CreateCityImpl(final CityEntityGateway cityEntityGateway) {
    this.cityEntityGateway = cityEntityGateway;
  }

  @Override
  public City createCity(final CreateCityCmd cmd) {
    return cityEntityGateway.create(cmd.getName(), cmd.getCode());
  }
}
