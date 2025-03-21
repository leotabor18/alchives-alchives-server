package com.alchives.alchiveserver.dto;

import java.util.List;

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

	private String email;

	// private MultipartFile image;

	private String batchYear;

	private String quotes;

	private String award;

	private String status;

	private Integer programId;
	// private List<AchievementDTO> achievements;
}
