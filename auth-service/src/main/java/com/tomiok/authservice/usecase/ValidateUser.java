package com.tomiok.authservice.usecase;

public interface ValidateUser {

  /**
   * Check if there is a user given a username and password
   *
   * @param username The username
   * @param password The user's password
   *
   * @return {@code true} if the user is valid, otherwise {@code false}
   */
  boolean isValid(String username, String password);
}
