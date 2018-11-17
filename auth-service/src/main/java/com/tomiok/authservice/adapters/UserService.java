package com.tomiok.authservice.adapters;

public interface UserService {

  String authenticateUserWithDatabase(String username, String password);
}
