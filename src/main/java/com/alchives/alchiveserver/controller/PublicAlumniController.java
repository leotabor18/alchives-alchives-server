package com.alchives.alchiveserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.service.AlumniService;

@RestController
@RequestMapping("/api/public/alumni")
public class PublicAlumniController {
  @Autowired
  AlumniService alumniService;

  @GetMapping
  public ResponseEntity<Object> getAlumni(@RequestParam("batchYear") String batchYear, 
    @RequestParam("programId") Integer programId) {
    List<Alumni> response = alumniService.getAlumni(batchYear, programId);
    if (response != null) {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);
  }
}
