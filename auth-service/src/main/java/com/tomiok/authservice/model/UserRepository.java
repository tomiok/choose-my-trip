package com.tomiok.authservice.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByUsernameAndPassword(String username, String password);
}
