package com.tomiok.authservice.usecase;

import com.tomiok.authservice.gateways.GatewaysConfig;
import com.tomiok.authservice.gateways.UserEntityGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GatewaysConfig.class)
public class ValidateUserUseCaseConfig {

  @Bean
  public ValidateUser validateUser(final UserEntityGateway userEntityGateway) {
    return new ValidateUserWithDatabase(userEntityGateway);
  }
}
