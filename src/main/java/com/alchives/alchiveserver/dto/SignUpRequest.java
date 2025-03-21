package com.alchives.alchiveserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
