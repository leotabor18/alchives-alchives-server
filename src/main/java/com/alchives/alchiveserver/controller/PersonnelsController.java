package com.alchives.alchiveserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alchives.alchiveserver.dto.PersonnelDTO;
import com.alchives.alchiveserver.service.PersonnelsService;

@RestController
@RequestMapping("/api/personnels")
public class PersonnelsController {
  @Autowired
  PersonnelsService personnelsService;

  @PostMapping(value = "/create", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> createAlumni(@ModelAttribute PersonnelDTO request, @RequestParam(name="image", required=false) MultipartFile image) {
    return personnelsService.createPersonnel(request, image);
  }
}
