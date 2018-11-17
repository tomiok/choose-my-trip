package com.tomiok.authservice.adapters;

import com.tomiok.authservice.usecase.ValidateUser;
import com.tomiok.authservice.usecase.ValidateUserUseCaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ValidateUserUseCaseConfig.class)
public class UserServiceConfig {

  @Bean
  public UserService userService(final ValidateUser validateUser) {
    return new UserServiceImpl(validateUser);
  }
}
