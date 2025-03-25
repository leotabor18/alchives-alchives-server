package com.alchives.alchiveserver.controller;

import java.io.IOException;
import java.util.List;

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

import com.alchives.alchiveserver.dto.InstituteDTO;
import com.alchives.alchiveserver.dto.PersonnelDTO;
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

  @PostMapping(value = "/create", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> createAlumni(@ModelAttribute InstituteDTO request, @RequestParam(name="image", required=false) MultipartFile image) {
    return instituteService.create(request, image);
  }

  @PatchMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
  public ResponseEntity<Object> updateAlumni(@PathVariable("id") Integer id, @ModelAttribute InstituteDTO request, @RequestParam(name="image", required=false) MultipartFile image) throws MessagingException, IOException {
    return instituteService.update(id, request, image);
  }

}
