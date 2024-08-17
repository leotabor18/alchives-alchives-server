package com.alchives.alchiveserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.alchives.alchiveserver.dto.PersonnelDTO;
import com.alchives.alchiveserver.entity.Personnel;
import com.alchives.alchiveserver.repository.PersonnelRepo;
import com.alchives.alchiveserver.util.StorageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonnelsService {
  @Autowired
  PersonnelRepo personnelRepo;

  public ResponseEntity<Object> createPersonnel(PersonnelDTO personnelDTO, MultipartFile image) {
    Optional<Personnel> optionalPersonnel = personnelRepo.findByFullName(personnelDTO.getFullName());
    if (optionalPersonnel.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    Personnel personnel = new Personnel();
    personnel.setDepartment(personnelDTO.getDepartment());
    personnel.setFullName(personnelDTO.getFullName());
    personnel.setPosition(personnelDTO.getPosition());
    personnel.setPrefix(personnelDTO.getPrefix());
    personnel.setSuffix(personnelDTO.getSuffix());

    if (image != null) {
      StorageUtil storageUtil = new StorageUtil();
      String path = storageUtil.uploadPersonnelImage(image);
      
      personnel.setProfilePic(path);
    }

    personnelRepo.save(personnel);

    return new ResponseEntity<>(personnel, HttpStatus.OK);
  } 

}
