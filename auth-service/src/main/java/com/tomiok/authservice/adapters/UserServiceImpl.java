package com.tomiok.authservice.adapters;

import com.tomiok.authservice.infra.JwtUtils;
import com.tomiok.authservice.usecase.ValidateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  private final ValidateUser validateUser;

  UserServiceImpl(final ValidateUser validateUser) {
    this.validateUser = validateUser;
  }

  @Override
  public String authenticateUserWithDatabase(final String username, final String password) {
    log.info("Validating user {}", username);

    if (validateUser.isValid(username, password)) {
      return JwtUtils.createJwtFromUsername(username);
    }
    log.warn("Authentication failed {}", username);
    throw new InvalidUserException("Invalid credentials for the given user");
  }
}
