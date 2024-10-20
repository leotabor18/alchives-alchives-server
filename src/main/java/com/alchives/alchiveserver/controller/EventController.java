package com.alchives.alchiveserver.controller;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alchives.alchiveserver.dto.AlumniDTO;
import com.alchives.alchiveserver.dto.EventDetailsRequestDTO;
import com.alchives.alchiveserver.dto.EventRequestDTO;
import com.alchives.alchiveserver.entity.Alumni;
import com.alchives.alchiveserver.entity.Event;
import com.alchives.alchiveserver.repository.EventRepo;
import com.alchives.alchiveserver.service.AlumniService;
import com.alchives.alchiveserver.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {
  @Autowired
  EventService eventService;

  @Autowired
  EventRepo eventRepo;


  @PostMapping(value = "/create")
  public ResponseEntity<Object> createAlumni(@RequestBody EventRequestDTO eventRequest) {
    return eventService.createEvent(eventRequest);
  }

  @GetMapping(value = "/get-current-event")
  public ResponseEntity<Object> getCurrentEvent(@RequestParam("programId") Integer programId, @RequestParam("batchYear") String batchYear) {
    return eventService.getEventDetails(programId, batchYear);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getEvent(@PathVariable("id") Integer id) {
    Event event = eventRepo.findById(id).orElseThrow();

    return new ResponseEntity<>(event, HttpStatus.OK);
  }

}
