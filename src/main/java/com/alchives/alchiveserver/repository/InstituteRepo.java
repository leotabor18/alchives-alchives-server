package com.alchives.alchiveserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Institute;

public interface InstituteRepo extends JpaRepository<Institute, Integer> {
  Optional<Institute> findByName(String name);

  Optional<Institute> findByInstituteId(Integer id);
}
