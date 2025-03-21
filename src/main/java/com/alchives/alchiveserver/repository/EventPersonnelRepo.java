package com.alchives.alchiveserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.EventPersonnel;

public interface EventPersonnelRepo extends JpaRepository<EventPersonnel, Integer>{

  List<EventPersonnel> findByEvent(Integer eventId);
}
