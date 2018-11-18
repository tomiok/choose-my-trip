package com.tomiok.authservice.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomiok.authservice.adapters.UserService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserAuthController.class)
public class UserAuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void authenticateWithDatabase() throws Exception {
    String username = "tomas";
    String password = "topsecrethashed";

    UserAndPasswordHttpRequest req = createHttpRequest(username, password);
    Mockito.when(userService.authenticateUserWithDatabase(username, password)).thenReturn("some-cool-token");

    String res = mockMvc
        .perform(post("/db")
            .content(mapper.writeValueAsString(req))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    assertThat(res).isEqualTo("some-cool-token");
  }

  @Test
  public void shouldFail_GivenEmptyPassword() throws Exception {
    String username = "tomas";
    String password = "topsecrethashed";

    UserAndPasswordHttpRequest req = createHttpRequest(username, StringUtils.EMPTY);

    mockMvc
        .perform(post("/db")
            .content(mapper.writeValueAsString(req))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    verifyZeroInteractions(userService);
  }

  @Test
  public void shouldFail_GivenNullPassword() throws Exception {
    String username = "tomas";
    String password = "topsecrethashed";

    UserAndPasswordHttpRequest req = createHttpRequest(username, null);

    mockMvc
        .perform(post("/db")
            .content(mapper.writeValueAsString(req))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

    verifyZeroInteractions(userService);
  }

  private UserAndPasswordHttpRequest createHttpRequest(String username, String password) {
    UserAndPasswordHttpRequest req = new UserAndPasswordHttpRequest();
    req.setUsername(username);
    req.setPassword(password);
    return req;
  }
}
