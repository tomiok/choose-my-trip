package com.tomiok.authservice.gateways;

import com.tomiok.authservice.model.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewaysConfig {

  private final UserRepository userRepository;

  public GatewaysConfig(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public UserEntityGateway userEntityGateway() {
    return new UserEntityGatewayImpl(userRepository);
  }
}
