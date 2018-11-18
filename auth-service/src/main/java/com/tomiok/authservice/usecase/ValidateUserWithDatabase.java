package com.tomiok.authservice.usecase;

import com.google.common.hash.Hashing;
import com.tomiok.authservice.gateways.UserEntityGateway;
import com.tomiok.authservice.model.User;
import java.nio.charset.StandardCharsets;

public class ValidateUserWithDatabase implements ValidateUser {

  private UserEntityGateway userEntityGateway;

  ValidateUserWithDatabase(final UserEntityGateway userEntityGateway) {
    this.userEntityGateway = userEntityGateway;
  }

  @Override
  public boolean isValid(final String username, final String password) {
    User user = userEntityGateway.findByUsernameAndPassword(username, hash(password));
    return user != null;
  }

  private static String hash(String password) {
    return Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
  }
}
