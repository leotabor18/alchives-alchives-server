package com.alchives.alchiveserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alchives.alchiveserver.entity.User;
import com.alchives.alchiveserver.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
  @Autowired
  private UserRepo userRepo;

  public User findUserByToken() {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> userOptional = userRepo.findByEmail(name);
    if (userOptional.isEmpty()) {
      return null;
    }
    
    return userOptional.get();
  }
}
