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

import com.alchives.alchiveserver.dto.AlumniDTO;
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.repository.AlumniRepo;
import com.alchives.alchiveserver.util.StorageUtil;

@Service
public class AlumniService {
  @Autowired
  AlumniRepo alumniRepo;


  public List<Alumni> getAlumni(String batchYear, Integer programid) {
    return alumniRepo.findByBatchYearAndProgramId(batchYear, programid);
  }

  public ResponseEntity<Object> createAlumni(AlumniDTO alumniDTO, MultipartFile image) {
    Optional<Alumni> optionalAlumni = alumniRepo.findByStudentNumber(alumniDTO.getStudentNumber());
    if (optionalAlumni.isPresent()) {
      throw new HttpClientErrorException(HttpStatus.CONFLICT);
    }
    
    Alumni alumni = new Alumni();
    alumni.setStudentNumber(alumniDTO.getStudentNumber());
    alumni.setAward(alumniDTO.getAward());
    alumni.setFirstName(alumniDTO.getFirstName());
    alumni.setLastName(alumniDTO.getLastName());
    alumni.setProgramId(alumniDTO.getProgramId());
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

    // if (image == null) {
    //   throw new BadRequestException("Failed to create entity user: File not found!");
    // }

    Alumni alumni = optionalAlumni.get();
    alumni.setAward(alumniDTO.getAward());
    alumni.setFirstName(alumniDTO.getFirstName());
    alumni.setLastName(alumniDTO.getLastName());
    alumni.setProgramId(alumniDTO.getProgramId());
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
