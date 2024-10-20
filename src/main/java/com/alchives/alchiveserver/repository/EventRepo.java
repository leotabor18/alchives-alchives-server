package com.alchives.alchiveserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Event;
import com.alchives.alchiveserver.entity.Program;

public interface EventRepo extends JpaRepository<Event, Integer> {

  List<Event> findByBatchYear(String batchYear);
  
}
