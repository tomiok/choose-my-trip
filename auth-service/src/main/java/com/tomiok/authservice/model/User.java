package com.tomiok.authservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  private String password;

  public User() {
    //JPA required
  }

  public User(final String username, final String password) {
    this.username = username;
    this.password = password;
  }
}
