package com.tomiok.itineraryservice.adapters.city;

import com.tomiok.itineraryservice.usecase.city.CreateCity;
import java.util.List;

public interface CityService {

  CreateCity.CitySummary createCity(CreateCity.CreateCityCmd cmd);

  List<CreateCity.CitySummary> findAll();
}
