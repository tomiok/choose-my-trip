package com.tomiok.authservice.adapters;

import com.tomiok.authservice.infra.JwtUtils;
import com.tomiok.authservice.usecase.ValidateUser;

public class UserServiceImpl implements UserService {

  private final ValidateUser validateUser;

  UserServiceImpl(final ValidateUser validateUser) {
    this.validateUser = validateUser;
  }

  @Override
  public String authenticateUserWithDatabase(final String username, final String password) {
    if (validateUser.isValid(username, password)) {
      return JwtUtils.createJwtFromUsername(username);
    }
    throw new InvalidUserException("Invalid credentials for the given user");
  }
}
