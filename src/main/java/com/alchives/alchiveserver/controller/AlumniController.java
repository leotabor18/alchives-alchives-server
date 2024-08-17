package com.alchives.alchiveserver.controller;

import javax.mail.Multipart;

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
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.service.AlumniService;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {
  @Autowired
  AlumniService alumniService;


  @PostMapping(value = "/create", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> createAlumni(@ModelAttribute AlumniDTO request, @RequestParam(name="image", required=false) MultipartFile image) {
    return alumniService.createAlumni(request, image);
  }

  @PatchMapping(value = "/{id}", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> updateAlumni(@PathVariable("id") Integer id, @ModelAttribute AlumniDTO request, @RequestParam(name="image", required=false) MultipartFile image) {
    return alumniService.updateAlumni(id, request, image);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getAlumni(@PathVariable("id") Integer id, @ModelAttribute AlumniDTO request, @RequestParam(name="image", required=false) MultipartFile image) {
    Alumni alumni = alumniService.getAlumniById(id);

    return new ResponseEntity<>(alumni, HttpStatus.OK);
  }
}
