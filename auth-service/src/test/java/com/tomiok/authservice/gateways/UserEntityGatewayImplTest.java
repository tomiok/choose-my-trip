package com.tomiok.authservice.gateways;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tomiok.authservice.model.User;
import com.tomiok.authservice.model.UserRepository;
import javax.persistence.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityGatewayImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserEntityGatewayImpl entityGateway;

  @Test
  public void findByUsernameAndPassword() {
    String username = "tomas";
    String pass = "supersecret1";
    User mockUser = new User(username, pass);

    //mock user do not have hashed pass
    when(userRepository.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(mockUser);
    User actual = entityGateway.findByUsernameAndPassword(username, pass);

    assertThat(actual.getUsername()).isEqualTo(username);
    assertThat(actual.getPassword()).isEqualTo(pass);

    verify(userRepository, times(1)).findUserByUsernameAndPassword(anyString(), anyString());
  }

  @Test
  public void shouldFail_WhenUserInNotInDatabase() {
    thrown.expect(EntityNotFoundException.class);
    String username = "tomas";
    String pass = "supersecret1";

    when(userRepository.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(null);
    entityGateway.findByUsernameAndPassword(username, pass);
    verify(userRepository, times(1)).findUserByUsernameAndPassword(anyString(), anyString());
  }
}
