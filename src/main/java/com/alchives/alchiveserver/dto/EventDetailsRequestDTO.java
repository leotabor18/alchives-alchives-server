package com.alchives.alchiveserver.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alchives.alchiveserver.entity.Program;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDetailsRequestDTO {

	private Integer programId;

	private String batchYear;	

}
