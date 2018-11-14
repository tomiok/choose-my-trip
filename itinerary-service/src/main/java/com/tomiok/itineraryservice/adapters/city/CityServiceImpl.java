package com.tomiok.itineraryservice.adapters.city;

import static java.util.stream.Collectors.toList;

import com.tomiok.itineraryservice.usecase.city.CreateCity;
import com.tomiok.itineraryservice.usecase.city.FindCity;
import java.util.List;

public class CityServiceImpl implements CityService {

  private final FindCity findCity;

  private final CreateCity createCity;

  CityServiceImpl(final FindCity findCity, final CreateCity createCity) {
    this.findCity = findCity;
    this.createCity = createCity;
  }

  @Override
  public CreateCity.CitySummary createCity(final CreateCity.CreateCityCmd cmd) {
    return new CreateCity.CitySummary(createCity.createCity(cmd));
  }

  @Override
  public List<CreateCity.CitySummary> findAll() {
    return findCity
        .findAll()
        .stream()
        .map(CreateCity.CitySummary::new)
        .collect(toList());
  }
}
