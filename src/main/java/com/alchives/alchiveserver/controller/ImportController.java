package com.alchives.alchiveserver.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alchives.alchiveserver.dto.ImportDTO;
import com.alchives.alchiveserver.dto.ImportReqDTO;
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.repository.AlumniRepo;
import com.alchives.alchiveserver.repository.ProgramRepo;

@RestController
@RequestMapping("/api/import-alumni")
public class ImportController {
  @Autowired
  AlumniRepo alumniRepo;

  @Autowired
  ProgramRepo programRepo;
  
  @PostMapping("/")
  public ResponseEntity<Object> uploadPersonsJson(@RequestBody ImportReqDTO reqAlumni){

    importAlumniJson(reqAlumni);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public void importAlumniJson(@RequestBody ImportReqDTO request){
    List<ImportDTO> importAlumni = request.getImportUsers();

    for (ImportDTO importDTO : importAlumni) {
      Optional<Alumni> alumOptional = alumniRepo.findByStudentNumber(importDTO.getStudentNumber());
      if (!alumOptional.isEmpty()) {
        continue;
      }

      Alumni alumni = new Alumni();
      alumni.setStudentNumber(importDTO.getStudentNumber());
      alumni.setFirstName(importDTO.getFirstName());
      alumni.setLastName(importDTO.getLastName());
      alumni.setBatchYear(importDTO.getBatch());

      Optional<Program> program = programRepo.findByName(importDTO.getProgram());
      if (!program.isEmpty()) {
        alumni.setProgram(program.get());
      }

      alumniRepo.save(alumni);
    }

  }
}