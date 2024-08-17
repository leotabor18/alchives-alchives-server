package com.alchives.alchiveserver.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonnelDTO {
  private Integer personnelId;

	private String prefix;

	private String fullName;

	private String suffix;

	private String department;
	
	private String position;
}
