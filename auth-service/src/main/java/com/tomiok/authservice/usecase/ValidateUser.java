package com.tomiok.authservice.usecase;

public interface ValidateUser {

  boolean isValid(String username, String password);
}
