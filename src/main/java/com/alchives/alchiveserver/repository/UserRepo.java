package com.alchives.alchiveserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.alchives.alchiveserver.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(@Param("email") String email);
  Page<User> findByRoleNot( String role, Pageable page);

  Boolean existsByEmail(String email);
}
