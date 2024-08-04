package com.alchives.alchiveserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.alchives.alchiveserver.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(@Param("email") String email);

  Boolean existsByEmail(String email);
}
