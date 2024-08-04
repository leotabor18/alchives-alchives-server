package com.alchives.alchiveserver.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumniDTO {
  private Integer alumniId;

	private String studentNumber;

	private String firstName;

	private String lastName;

	private String suffix;

	// private MultipartFile image;

	private String batchYear;

	private String quotes;

	private String award;

	private String status;

	private Integer programId; 
}
