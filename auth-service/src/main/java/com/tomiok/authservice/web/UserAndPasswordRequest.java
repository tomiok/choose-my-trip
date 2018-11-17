package com.tomiok.authservice.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class UserAndPasswordRequest {

  private String username;

  private String password;
}
