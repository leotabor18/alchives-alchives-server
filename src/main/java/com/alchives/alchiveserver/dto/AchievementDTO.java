package com.alchives.alchiveserver.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AchievementDTO {
  private Integer achievementId;

	private Integer alumniId;

	private String text;

	private String date;
}
