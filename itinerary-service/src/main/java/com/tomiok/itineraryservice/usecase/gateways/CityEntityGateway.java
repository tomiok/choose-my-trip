package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.City;
import java.util.List;

public interface CityEntityGateway {

  City create(String name, String code);

  void delete(long id);

  void delete(String code);

  List<City> findAll();

  boolean existsWithNameOrCode(String nameOrCode);

  City findByNameOrCode(String nameOrCode);
}
