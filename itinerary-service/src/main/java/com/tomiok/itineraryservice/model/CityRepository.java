package com.tomiok.itineraryservice.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Long> {

  void deleteByCode(String code);

  @Query(value = "select c from City c where ("
                 + " c.name = :city or c.code = :city)")
  City findCitiesByNameOrCode(@Param("city") String cityNameOrCode);
}
