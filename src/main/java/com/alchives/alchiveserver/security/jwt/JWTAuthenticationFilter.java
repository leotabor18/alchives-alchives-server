package com.alchives.alchiveserver.security.jwt;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alchives.alchiveserver.dto.UserCredentialDTO;
import com.alchives.alchiveserver.entity.User;
import com.alchives.alchiveserver.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private static final String BLOCKED = "BLO";
  
  private UserRepo userRepo;

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserRepo userRepo) {
    this.authenticationManager = authenticationManager;
    this.userRepo = userRepo;
    setFilterProcessesUrl(SecurityConstants.SIGN_IN_URL);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      UserCredentialDTO creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentialDTO.class);

      Authentication auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

      Optional<User> user = userRepo.findByEmail(creds.getEmail());
      
      if (user.isPresent()) {
        if (auth.isAuthenticated()) {
          log.info("User {}", user.get().getUserId() + " authentication successful. ");
        } else {
          log.error("User {}", user.get().getUserId() + " authentication failed. Invalid credentials ");
        }
      }

      return auth;
    } catch (BadCredentialsException | IOException e) {
      log.info("Error: {}", e);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
    
    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication auth) throws IOException {
    
    String username = ((UserDetails) auth.getPrincipal()).getUsername();

    Optional<User> optionalUser = userRepo.findByEmail(username);

    if (optionalUser.isEmpty()) {
      return;
    }
    
    User user = optionalUser.get();

    String accessToken  = JWTUtil.createToken(user, SecurityConstants.ACCESS_TOKEN_EXPIRATION);
    String refreshToken = JWTUtil.createToken(user, SecurityConstants.REFRESH_TOKEN_EXPIRATION);

    Map<String, String> tokens = new HashMap<>();

    tokens.put(SecurityConstants.ACCESS_TOKEN, accessToken);
    tokens.put(SecurityConstants.REFRESH_TOKEN, refreshToken);

    response.setContentType(SecurityConstants.APPLICATION_JSON_CONTENT_TYPE);
    new ObjectMapper().writeValue(response.getOutputStream(), tokens); 
  }

}
