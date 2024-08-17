package com.alchives.alchiveserver.service;

import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alchives.alchiveserver.dto.ProgramDTO;
import com.alchives.alchiveserver.entity.Institute;
import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.repository.InstituteRepo;
import com.alchives.alchiveserver.repository.ProgramRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramService {

  @Autowired
  ProgramRepo programRepo;

  @Autowired
  InstituteRepo instituteRepo;

  public Program getProgram(Integer id) {
    Optional<Program> programOptional = programRepo.findById(id);
    if (!programOptional.isPresent()) {
      throw new NotFoundException();
    }

    return programOptional.get();
  }

  public ResponseEntity<Object> createProgram(ProgramDTO request) {
    String name = request.getName();
    log.error("name {}", name);
    Optional<Program> programOptional = programRepo.findByName(name);
    if (programOptional.isPresent()) {
      log.error("Program name {} is already exist", name);
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    String desciption = request.getDescription();
    Integer instituteId = request.getId();
    log.info("instituteId {}", instituteId);
    Optional<Institute> instituteOptional = instituteRepo.findByInstituteId(instituteId);
    if (!instituteOptional.isPresent()) {
      log.error("Institute id is not exist");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Program program = new Program();
    program.setName(name);
    program.setDescription(desciption);
    program.setInstitute(instituteOptional.get());

    programRepo.save(program);

    return new ResponseEntity<>(program, HttpStatus.OK);
  }

  public ResponseEntity<Object> updateProgram(Integer id, ProgramDTO request) {
    String name = request.getName();
    Optional<Program> programOptional = programRepo.findById(id);
    if (!programOptional.isPresent()) {
      log.error("Program name {} is not exist", name);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    String desciption = request.getDescription();
    Integer instituteId = request.getId();
    log.info("institute {}", instituteId);
    Optional<Institute> instituteOptional = instituteRepo.findByInstituteId(instituteId);
    if (!instituteOptional.isPresent()) {
      log.error("Institute name is not exist");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Program program = programOptional.get();
    program.setName(name);
    program.setDescription(desciption);
    program.setInstitute(instituteOptional.get());

    programRepo.save(program);

    return new ResponseEntity<>(program, HttpStatus.OK);
  }
}
