package com.alchives.alchiveserver.service;

import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.repository.ProgramRepo;

@Service
public class ProgramService {

  @Autowired
  ProgramRepo programRepo;
  public Program getProgram(Integer id) {
    Optional<Program> programOptional = programRepo.findById(id);
    if (!programOptional.isPresent()) {
      throw new NotFoundException();
    }

    return programOptional.get();
  }
}
