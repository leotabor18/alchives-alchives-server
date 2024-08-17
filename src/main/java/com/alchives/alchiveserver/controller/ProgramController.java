package com.alchives.alchiveserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alchives.alchiveserver.dto.ProgramDTO;
import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.service.ProgramService;

@RestController
@RequestMapping("/api/program")
public class ProgramController {
  @Autowired
  ProgramService programService;

  @GetMapping("/{id}")
  public ResponseEntity<Object> getInstituteById(@PathVariable("id") Integer id) {
    Program program = programService.getProgram(id);
    return new ResponseEntity<>(program, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Object> createInstitute(@RequestBody ProgramDTO request) {
    return programService.createProgram(request);
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<Object> updateAlumni(@PathVariable("id") Integer id,@RequestBody ProgramDTO request) {
    return programService.updateProgram(id, request);
  }
}
