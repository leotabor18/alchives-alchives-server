package com.alchives.alchiveserver.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alchives.alchiveserver.dto.AlumniDTO;
import com.alchives.alchiveserver.dto.PersonnelDTO;
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.repository.PersonnelRepo;
import com.alchives.alchiveserver.service.PersonnelsService;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelsController {
  @Autowired
  PersonnelsService personnelsService;

  @Autowired
  PersonnelRepo personnelRepo;

  @PostMapping(value = "/create", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> createAlumni(@ModelAttribute PersonnelDTO request, @RequestParam(name="image", required=false) MultipartFile image) {
    return personnelsService.createPersonnel(request, image);
  }

  @PatchMapping(value = "/{id}", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> updateAlumni(@PathVariable("id") Integer id, @ModelAttribute PersonnelDTO request, @RequestParam(name="image", required=false) MultipartFile image) throws MessagingException, IOException {
    return personnelsService.updatePersonnel(id, request, image);
  }
}
