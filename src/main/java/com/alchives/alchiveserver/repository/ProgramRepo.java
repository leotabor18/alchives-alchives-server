package com.alchives.alchiveserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Program;

public interface ProgramRepo extends JpaRepository<Program, Integer> {
  Optional<Program> findByProgramId(Integer programId);

  Page<Program> findByNameContaining(String programName, Pageable page);

  Page<Program> findByInstituteInstituteId(Integer instituteId, Pageable page);

  Optional<Program> findByName(String name);

}
