package com.alchives.alchiveserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {
  
}
