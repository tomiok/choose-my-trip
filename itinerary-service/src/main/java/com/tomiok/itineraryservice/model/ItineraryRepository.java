package com.tomiok.itineraryservice.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

  @Query(value = "select it from Itinerary it where (it.departureCity.name = :city"
                 + " or it.departureCity.code = :city)")
  List<Itinerary> findAllByDepartureCity(@Param("city") String cityNameOrCode);
}
