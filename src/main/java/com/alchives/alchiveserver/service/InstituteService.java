package com.alchives.alchiveserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alchives.alchiveserver.entity.Institute;
import com.alchives.alchiveserver.repository.InstituteRepo;

@Service
public class InstituteService {
  @Autowired
  InstituteRepo instituteRepo;

  public List<Institute> getInstitutes() {
    return instituteRepo.findAll();
  }
}
