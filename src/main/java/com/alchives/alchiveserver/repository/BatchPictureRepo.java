package com.alchives.alchiveserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.BatchPicture;

public interface BatchPictureRepo extends JpaRepository<BatchPicture, Integer> {
  
}
