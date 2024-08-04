package com.alchives.alchiveserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alchives.alchiveserver.entity.Institute;
import com.alchives.alchiveserver.service.InstituteService;

@RestController
@RequestMapping("/api/public/institutes")
public class InstituteController {
  @Autowired
  InstituteService instituteService;

  @GetMapping
  public ResponseEntity<Object> getInstitutes() {
    List<Institute> institutes = instituteService.getInstitutes();
    return new ResponseEntity<>(institutes, HttpStatus.OK);
  }

}
