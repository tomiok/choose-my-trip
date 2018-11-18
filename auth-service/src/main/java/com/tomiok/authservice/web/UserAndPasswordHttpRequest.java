package com.tomiok.authservice.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class UserAndPasswordHttpRequest {

  @NotNull(message = "username cannot be null")
  @NotEmpty(message = "username cannot be empty")
  private String username;

  @NotNull(message = "password cannot be null")
  @NotEmpty(message = "password cannot be empty")
  private String password;
}
