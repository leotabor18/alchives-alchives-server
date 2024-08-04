package com.alchives.alchiveserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Program;

public interface ProgramRepo extends JpaRepository<Program, Integer> {

}
