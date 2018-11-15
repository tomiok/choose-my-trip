package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.City;
import com.tomiok.itineraryservice.model.CityRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class CityEntityGatewayImpl implements CityEntityGateway {

  private final CityRepository cityRepository;

  CityEntityGatewayImpl(final CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Override
  public City create(final String name, final String code) {
    City city = new City(name, code);
    return cityRepository.save(city);
  }

  @Transactional
  @Override
  public void delete(final long id) {
    cityRepository.deleteById(id);
  }

  @Override
  public void delete(final String code) {
    cityRepository.deleteByCode(code);
  }

  @Override
  public List<City> findAll() {
    return cityRepository.findAll();
  }

  @Override
  public boolean existsWithNameOrCode(final String nameOrCode) {
    return cityRepository.findCitiesByNameOrCode(nameOrCode) != null;
  }

  @Override
  public City findByNameOrCode(final String nameOrCode) {
    City city = cityRepository.findCitiesByNameOrCode(nameOrCode);
    if (city == null) {
      throw new CityNotFoundException("The city " + nameOrCode + " is not in the database");
    }

    return city;
  }
}
