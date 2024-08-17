package com.alchives.alchiveserver.service;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.alchives.alchiveserver.dto.AlumniDTO;
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.repository.AlumniRepo;
import com.alchives.alchiveserver.repository.ProgramRepo;
import com.alchives.alchiveserver.util.StorageUtil;

@Service
public class AlumniService {
  @Autowired
  AlumniRepo alumniRepo;
  
  @Autowired
  ProgramRepo programRepo;


  public List<Alumni> getAlumni(String batchYear, Integer programid) {
    return alumniRepo.findByBatchYearAndProgramProgramId(batchYear, programid);
  }

  public Alumni getAlumniById(Integer alumniId) {
    Optional<Alumni> aluOptional = alumniRepo.findById(alumniId);
    if (!aluOptional.isPresent()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    Alumni alumni = aluOptional.get();
    return alumni;
  }

  public ResponseEntity<Object> createAlumni(AlumniDTO alumniDTO, MultipartFile image) {
    Optional<Alumni> optionalAlumni = alumniRepo.findByStudentNumber(alumniDTO.getStudentNumber());
    if (optionalAlumni.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    
    Optional<Program> programOptional = programRepo.findByProgramId(alumniDTO.getProgramId());

    if (!programOptional.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Program program = programOptional.get();

    Alumni alumni = new Alumni();
    alumni.setStudentNumber(alumniDTO.getStudentNumber());
    alumni.setAward(alumniDTO.getAward());
    alumni.setFirstName(alumniDTO.getFirstName());
    alumni.setLastName(alumniDTO.getLastName());
    alumni.setProgram(program);
    alumni.setBatchYear(alumniDTO.getBatchYear());
    alumni.setQuotes(alumniDTO.getQuotes());

    if (image != null) {
      StorageUtil storageUtil = new StorageUtil();
      String path = storageUtil.uploadImage(image);
      
      alumni.setImage(path);
    }

    alumniRepo.save(alumni);

    return new ResponseEntity<>(alumni, HttpStatus.OK);
  }

  public ResponseEntity<Object> updateAlumni(Integer id, AlumniDTO alumniDTO, MultipartFile image) {
    Optional<Alumni> optionalAlumni = alumniRepo.findById(id);
    if (!optionalAlumni.isPresent()) {
      throw new BadRequestException("Alumni is not exist");
    }

    Optional<Program> programOptional = programRepo.findByProgramId(alumniDTO.getProgramId());

    if (!programOptional.isPresent()) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    Program program = programOptional.get();
    Alumni alumni = optionalAlumni.get();
    alumni.setAward(alumniDTO.getAward());
    alumni.setFirstName(alumniDTO.getFirstName());
    alumni.setLastName(alumniDTO.getLastName());
    alumni.setProgram(program);
    alumni.setBatchYear(alumniDTO.getBatchYear());
    alumni.setQuotes(alumniDTO.getQuotes());
  
    if (image != null) {
      StorageUtil storageUtil = new StorageUtil();
      String fileName = storageUtil.getImagePathName(image);
      if (!fileName.equals(alumni.getImage())) {
        String path = storageUtil.uploadImage(image);
        alumni.setImage(path);
      }
    }

    alumniRepo.save(alumni);

    return new ResponseEntity<>(alumni, HttpStatus.OK);
  }


}
