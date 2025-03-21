package com.alchives.alchiveserver.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Personnel;

public interface PersonnelRepo extends JpaRepository<Personnel, Integer>{
  Optional<Personnel> findByFullName(String fullName);

  Optional<Personnel> findByPersonnelId(Integer id);
}
