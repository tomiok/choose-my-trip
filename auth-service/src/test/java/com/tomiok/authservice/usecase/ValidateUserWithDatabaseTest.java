package com.tomiok.authservice.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tomiok.authservice.gateways.UserEntityGateway;
import com.tomiok.authservice.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidateUserWithDatabaseTest {

  @Mock
  private UserEntityGateway entityGateway;

  @InjectMocks
  private ValidateUserWithDatabase validateUserWithDatabase;

  @Test
  public void isValid() {
    String username = "tomasf";
    String password = "secret9090";
    User user = new User(username, password);
    when(entityGateway.findByUsernameAndPassword(anyString(), anyString())).thenReturn(user);

    boolean actual = validateUserWithDatabase.isValid(username, password);

    assertThat(actual).isTrue();
    verify(entityGateway, times(1)).findByUsernameAndPassword(anyString(), anyString());
  }

  @Test
  public void shouldRetunrFalse_GivenInvalidUser() {
    String username = "tomasf";
    String password = "secret9090";
    User user = new User(username, password);
    when(entityGateway.findByUsernameAndPassword(anyString(), anyString())).thenReturn(null);

    boolean actual = validateUserWithDatabase.isValid(username, password);

    assertThat(actual).isFalse();
    verify(entityGateway, times(1)).findByUsernameAndPassword(anyString(), anyString());
  }
}
