package com.tomiok.authservice.gateways;

import com.tomiok.authservice.model.User;
import com.tomiok.authservice.model.UserRepository;
import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserEntityGatewayImpl implements UserEntityGateway {

  private static final Logger log = LoggerFactory.getLogger(UserEntityGatewayImpl.class);

  private UserRepository userRepository;

  UserEntityGatewayImpl(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findByUsernameAndPassword(final String username, final String password) {
    User user = userRepository.findUserByUsernameAndPassword(username, password);
    if (user == null) {
      log.warn("username or password are not valid");
      throw new EntityNotFoundException();
    }

    return user;
  }
}
