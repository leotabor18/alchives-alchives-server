package com.alchives.alchiveserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.alchives.alchiveserver.dto.InstituteDTO;
import com.alchives.alchiveserver.entity.Institute;
import com.alchives.alchiveserver.repository.InstituteRepo;
import com.alchives.alchiveserver.util.StorageUtil;

@Service
public class InstituteService {
  @Autowired
  InstituteRepo instituteRepo;

  public List<Institute> getInstitutes() {
    return instituteRepo.findAll();
  }

  public ResponseEntity<Object> create(InstituteDTO instituteDTO, MultipartFile image) {
    Optional<Institute> optionalInstitute = instituteRepo.findByName(instituteDTO.getName());
    if (optionalInstitute.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    Institute institute = new Institute();
    institute.setName(instituteDTO.getName());
    institute.setDescription(instituteDTO.getDescription());
    institute.setColor(instituteDTO.getColor());

    if (image != null) {
      StorageUtil storageUtil = new StorageUtil();
      String path = storageUtil.uploadImage(image);
      institute.setImage(path);
    }

    instituteRepo.save(institute);

    return new ResponseEntity<>(institute, HttpStatus.OK);
  }
  public ResponseEntity<Object> update(Integer id, InstituteDTO instituteDTO, MultipartFile image) {
    Optional<Institute> optionalInstitute = instituteRepo.findByInstituteId(id);
    if (optionalInstitute.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    Institute institute = optionalInstitute.get();
    if (!institute.getName().equals(instituteDTO.getName())) {
      Optional<Institute> optionalInstitute1 = instituteRepo.findByName(instituteDTO.getName());
      if (optionalInstitute1.isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT);
      }
    }

    institute.setName(instituteDTO.getName());
    institute.setDescription(instituteDTO.getDescription());
    institute.setColor(instituteDTO.getColor());

    if (image != null && institute.getImage() != image.getOriginalFilename()) {
      StorageUtil storageUtil = new StorageUtil();
      String path = storageUtil.uploadImage(image);
      institute.setImage(path);
    }

    instituteRepo.save(institute);

    return new ResponseEntity<>(institute, HttpStatus.OK);
  }

}
