package com.alchives.alchiveserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Event;
import com.alchives.alchiveserver.entity.EventProgram;

public interface EventProgramRepo extends JpaRepository<EventProgram, Integer> {
  
}
