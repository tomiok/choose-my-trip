package com.tomiok.authservice.adapters;

public interface UserService {

  /**
   * Authenticate the user with a database
   *
   * @param username The username
   * @param password The user's password
   *
   * @return A JWT if the authentication is OK, otherwise throws {@link InvalidUserException}
   */
  String authenticateUserWithDatabase(String username, String password);
}
