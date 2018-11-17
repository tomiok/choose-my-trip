package com.tomiok.authservice.web;

import static org.springframework.http.ResponseEntity.ok;

import com.tomiok.authservice.adapters.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

  private final UserService userService;

  public UserAuthController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<String> authenticateWithDatabase(@RequestBody UserAndPasswordRequest req) {
    String jwt = userService.authenticateUserWithDatabase(req.getUsername(), req.getPassword());
    return ok(jwt);
  }
}