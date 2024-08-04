package com.alchives.alchiveserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String code;
    private String newPassword;
}

