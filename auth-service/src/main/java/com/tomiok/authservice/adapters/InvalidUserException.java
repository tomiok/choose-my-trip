package com.tomiok.authservice.adapters;

public class InvalidUserException extends RuntimeException {

  InvalidUserException(final String message) {
    super(message);
  }
}
