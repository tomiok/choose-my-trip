package com.tomiok.authservice.gateways;

import com.tomiok.authservice.model.User;

public interface UserEntityGateway {

  User findByUsernameAndPassword(String username, String password);
}
