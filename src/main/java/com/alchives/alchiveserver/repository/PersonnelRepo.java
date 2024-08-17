package com.alchives.alchiveserver.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Personnel;

public interface PersonnelRepo extends JpaRepository<Personnel, Integer>{
  
}
