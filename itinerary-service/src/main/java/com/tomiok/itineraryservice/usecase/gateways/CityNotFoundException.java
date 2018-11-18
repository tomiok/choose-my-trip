package com.tomiok.itineraryservice.usecase.gateways;

public class CityNotFoundException extends RuntimeException {

  public CityNotFoundException(final String message) {
    super(message);
  }
}
