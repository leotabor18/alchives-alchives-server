package com.alchives.alchiveserver.service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.alchives.alchiveserver.dto.AlumniDTO;
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.entity.User;
import com.alchives.alchiveserver.repository.AlumniRepo;
import com.alchives.alchiveserver.repository.ProgramRepo;
import com.alchives.alchiveserver.repository.UserRepo;
import com.alchives.alchiveserver.util.StorageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlumniService {
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String DIGITS = "0123456789";
  private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";
  private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

  @Autowired
  AlumniRepo alumniRepo;

  @Autowired
  UserRepo userRepo;
  
  @Autowired
  ProgramRepo programRepo;

  @Autowired
  EmailService emailService;

  private static final SecureRandom RANDOM = new SecureRandom();

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

  public ResponseEntity<Object> createAlumni(AlumniDTO alumniDTO, MultipartFile image) throws MessagingException, IOException {
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
    alumni.setEmail(alumniDTO.getEmail());

    if (image != null) {
      StorageUtil storageUtil = new StorageUtil();
      String path = storageUtil.uploadImage(image);
      
      alumni.setImage(path);
    }

    alumniRepo.save(alumni);

    if (alumniDTO.getEmail() != "" || alumniDTO.getEmail() != null) {
      User user = new User();
      user.setEmail(alumniDTO.getEmail());
      user.setFirstName(alumniDTO.getFirstName());
      user.setLastName(alumniDTO.getLastName());
      user.setRole("ALUMNI");

      String password = generateSecurePassword(10);
      System.out.println("Generated Password: " + password);

      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      user.setPassword(passwordEncoder.encode(password));
      userRepo.save(user);

      Locale locale = Locale.getDefault();
      emailService.sendUserEmail(user, locale, password);
    }

    return new ResponseEntity<>(alumni, HttpStatus.OK);
  }

  public ResponseEntity<Object> updateAlumni(Integer id, AlumniDTO alumniDTO, MultipartFile image) throws MessagingException, IOException {
    Optional<Alumni> optionalAlumni = alumniRepo.findById(id);
    if (!optionalAlumni.isPresent()) {
      throw new BadRequestException("Alumni is not exist");
    }

    Optional<Program> programOptional = programRepo.findByProgramId(alumniDTO.getProgramId());
    log.info("alumniDTO.getProgramId() {}", alumniDTO.getProgramId());
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
    alumni.setEmail(alumniDTO.getEmail());

    if (image != null) {
      StorageUtil storageUtil = new StorageUtil();
      String fileName = storageUtil.getImagePathName(image);
      if (!fileName.equals(alumni.getImage())) {
        String path = storageUtil.uploadImage(image);
        alumni.setImage(path);
      }
    }

    alumniRepo.save(alumni);
    Boolean userOptional = userRepo.existsByEmail(alumniDTO.getEmail()); 

    if ((alumniDTO.getEmail() != "" || alumniDTO.getEmail() != null)) {
      User user = new User();
      
      if (userOptional) {
        Optional<User> opUser = userRepo.findByEmail(alumniDTO.getEmail());
        user = opUser.get();
      }
      user.setEmail(alumniDTO.getEmail());
      user.setFirstName(alumniDTO.getFirstName());
      user.setLastName(alumniDTO.getLastName());
      user.setRole("ALUMNI");
      
      log.info("userOptional) {}", userOptional);
      if (!userOptional) {
        String password = generateSecurePassword(10);
        System.out.println("Generated Password: " + password);
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
        user.setPassword(passwordEncoder.encode(password));
        
        Locale locale = Locale.getDefault();
        emailService.sendUserEmail(user, locale, password);
      }
      

      userRepo.save(user);
    }
    
    return new ResponseEntity<>(alumni, HttpStatus.OK);
  }

  public static String generateSecurePassword(int length) {
    if (length < 8) {
        throw new IllegalArgumentException("Password length must be at least 8 characters.");
    }

    StringBuilder password = new StringBuilder(length);

    // Ensure the password contains at least one character from each character set
    password.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
    password.append(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
    password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
    password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

    // Fill the remaining characters randomly from all character sets
    for (int i = 4; i < length; i++) {
        password.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));
    }

    // Shuffle the characters for better security
    return shuffleString(password.toString());
  }

  private static String shuffleString(String input) {
    StringBuilder shuffled = new StringBuilder(input.length());
    int[] indices = RANDOM.ints(0, input.length()).distinct().limit(input.length()).toArray();
    for (int index : indices) {
        shuffled.append(input.charAt(index));
    }
    return shuffled.toString();
  }


}
