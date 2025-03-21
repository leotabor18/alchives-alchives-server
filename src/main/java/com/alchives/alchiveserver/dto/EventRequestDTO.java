package com.alchives.alchiveserver.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alchives.alchiveserver.entity.Program;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequestDTO {
  // private Integer personnelId;

	private String eventName;

	private String batchYear;
	
	private String eventTheme;
	
	private String eventVenue;
	
	private Date eventDate;
	
	private String eventThemeSongTitle;
	
	private String eventThemeSong;

	List<String> programs;

	List<EventPersonnelDTO> eventPersonnelList;
}
