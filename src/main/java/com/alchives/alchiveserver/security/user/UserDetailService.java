package com.alchives.alchiveserver.security.user;

import java.util.ArrayList;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alchives.alchiveserver.dto.UserCredentialDTO;
import com.alchives.alchiveserver.entity.User;
import com.alchives.alchiveserver.mapper.HibernateFieldMapper;
import com.alchives.alchiveserver.repository.UserRepo;
import com.alchives.alchiveserver.security.jwt.SecurityConstants;

@Service
public class UserDetailService implements UserDetailsService {
  @Autowired
  UserRepo userRepo;

  private DozerBeanMapper dozerBeanMapper;

  @Autowired
  public UserDetailService() {
    this.dozerBeanMapper = new DozerBeanMapper();
    this.dozerBeanMapper.setCustomFieldMapper(new HibernateFieldMapper());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Optional<User> optionalUser = userRepo.findByEmail(username);

      if (optionalUser.isEmpty()) {
        throw new UsernameNotFoundException(SecurityConstants.USER_NOT_FOUND);
      }
  
      User user = optionalUser.get();
      UserCredentialDTO cred = new UserCredentialDTO();
      cred.setEmail(user.getEmail());
      cred.setPassword(user.getPassword());
  
      return new UserPrincipal(dozerBeanMapper.map(cred, UserCredentialDTO.class), new ArrayList<>());
    } catch (Exception e){
      throw new UsernameNotFoundException(e.getMessage());
    }
  }
}
