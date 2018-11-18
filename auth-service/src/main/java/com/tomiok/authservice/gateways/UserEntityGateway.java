package com.tomiok.authservice.gateways;

import com.tomiok.authservice.model.User;
import javax.persistence.EntityNotFoundException;

public interface UserEntityGateway {

  /**
   * Find a user given a username and password.
   *
   * @param username The username
   * @param password The user's password
   *
   * @return The user in the database. If the params do not match, an {@link EntityNotFoundException} will be thrown.
   */
  User findByUsernameAndPassword(String username, String password);
}
