package com.alchives.alchiveserver.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alchives.alchiveserver.dto.PasswordResetDTO;
import com.alchives.alchiveserver.dto.ResetPasswordRequest;
import com.alchives.alchiveserver.dto.SignUpRequest;
import com.alchives.alchiveserver.entity.User;
import com.alchives.alchiveserver.service.AuthService;
import com.alchives.alchiveserver.service.UserService;
import com.alchives.alchiveserver.service.VerificationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  UserService userService;

  @Autowired
  AuthService authService;

  @Autowired
  VerificationService verificationService;
  
  @PostMapping("/signup")
  public ResponseEntity<Object> signUp(@RequestBody SignUpRequest request) throws MessagingException, IOException {
    User response = authService.registerUser(request);
    if (response != null) {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/user")
  public ResponseEntity<Object> findUserByToken() {
    User response = userService.findUserByToken();
    if (response != null) {
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/reset-password")
  public ResponseEntity<Object> resetPassword(@RequestBody PasswordResetDTO request)
      throws MessagingException, IOException {
    return authService.forgotPassword(request);
  }

  @GetMapping("/new-password/{code:.+}")
  public ResponseEntity<Object> newPassword(@PathVariable("code") String code,
      @RequestParam("resendRequest") Boolean resendRequest)
      throws NotFoundException {
    return verificationService.checkNewPasswordVerification(code, resendRequest);
  }

  @PostMapping("/new-password")
  public ResponseEntity<Object> newPassword(@RequestBody ResetPasswordRequest request) {
    return authService.newPassword(request);
  }
}
