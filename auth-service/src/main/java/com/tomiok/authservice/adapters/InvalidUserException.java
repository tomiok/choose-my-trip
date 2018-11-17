package com.tomiok.authservice.adapters;

class InvalidUserException extends RuntimeException {

  InvalidUserException(final String message) {
    super(message);
  }
}
