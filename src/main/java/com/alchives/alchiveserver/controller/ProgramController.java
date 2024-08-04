package com.alchives.alchiveserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.service.ProgramService;

@RestController
@RequestMapping("/api/public/programs")
public class ProgramController {
  @Autowired
  ProgramService programService;

  @GetMapping("/{id}")
  public ResponseEntity<Object> getInstituteById(@PathVariable("id") Integer id) {
    Program program = programService.getProgram(id);
    return new ResponseEntity<>(program, HttpStatus.OK);
  }
}
