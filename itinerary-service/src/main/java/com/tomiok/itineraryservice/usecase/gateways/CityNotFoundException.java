package com.tomiok.itineraryservice.usecase.gateways;

public class CityNotFoundException extends RuntimeException {

  CityNotFoundException(final String message) {
    super(message);
  }
}
