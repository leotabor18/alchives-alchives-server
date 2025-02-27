package com.alchives.alchiveserver.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCredentialDTO implements Serializable {
  private static final long serialVersionUID = 1905122041950251207L;

	private String userId;
	private String email;
	private String password;
}
