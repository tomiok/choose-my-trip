package com.tomiok.authservice.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tomiok.authservice.usecase.ValidateUser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private ValidateUser validateUser;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  public void authenticateUserWithDatabase() {
    when(validateUser.isValid(anyString(), anyString())).thenReturn(Boolean.TRUE);
    String jwt = userService.authenticateUserWithDatabase("user", "secretpass");

    assertThat(jwt).isNotBlank();
    assertThat(jwt).isNotEmpty();

    verify(validateUser, times(1)).isValid(anyString(), anyString());
  }

  @Test
  public void shouldFailGivenInvalidUserOrPass() {
    thrown.expect(InvalidUserException.class);

    when(validateUser.isValid(anyString(), anyString())).thenReturn(Boolean.FALSE);
    userService.authenticateUserWithDatabase("user", "invalidpass");
    verify(validateUser, times(1)).isValid(anyString(), anyString());
  }
}
