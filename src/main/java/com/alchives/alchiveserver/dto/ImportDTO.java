package com.alchives.alchiveserver.dto;

import lombok.Data;

@Data
public class ImportDTO {
  private String studentNumber;
  private String firstName;
  private String lastName;
  private String program;
  private String institute;
  private String batch;
}
