package com.alchives.alchiveserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Content;

public interface ContentRepo extends JpaRepository<Content, Integer>{
  
}
