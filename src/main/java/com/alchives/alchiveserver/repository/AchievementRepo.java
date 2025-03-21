package com.alchives.alchiveserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alchives.alchiveserver.entity.Achievement;

@RepositoryRestResource
public interface AchievementRepo extends JpaRepository<Achievement, Integer>{
  // List<Achievements> findByAlumniId(Integer alumniId);


  @Query(value = "SELECT * FROM achievement WHERE alumni_id =:alumniId", nativeQuery = true)
  List<Achievement> findByAlumniId(Integer alumniId);
}
