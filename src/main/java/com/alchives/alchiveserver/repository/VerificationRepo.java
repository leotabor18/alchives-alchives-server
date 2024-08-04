package com.alchives.alchiveserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alchives.alchiveserver.entity.Verification;

@RepositoryRestResource
public interface VerificationRepo extends JpaRepository<Verification, Integer>{
  Optional<Verification> findByUserId(Integer userId);
    
  Optional<Verification> findByCode(String code);
}
