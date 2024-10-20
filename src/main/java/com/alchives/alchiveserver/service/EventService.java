package com.alchives.alchiveserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.alchives.alchiveserver.dto.EventDetailsRequestDTO;
import com.alchives.alchiveserver.dto.EventPersonnelDTO;
import com.alchives.alchiveserver.dto.EventPersonnelResponseDTO;
import com.alchives.alchiveserver.dto.EventRequestDTO;
import com.alchives.alchiveserver.dto.EventResponseDTO;
import com.alchives.alchiveserver.dto.PersonnelDTO;
import com.alchives.alchiveserver.entity.Event;
import com.alchives.alchiveserver.entity.EventPersonnel;
import com.alchives.alchiveserver.entity.EventProgram;
import com.alchives.alchiveserver.entity.Personnel;
import com.alchives.alchiveserver.entity.Program;
import com.alchives.alchiveserver.repository.EventPersonnelRepo;
import com.alchives.alchiveserver.repository.EventProgramRepo;
import com.alchives.alchiveserver.repository.EventRepo;
import com.alchives.alchiveserver.repository.PersonnelRepo;
import com.alchives.alchiveserver.repository.ProgramRepo;
import com.alchives.alchiveserver.util.StorageUtil;
import com.amazonaws.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventService {
  @Autowired
  PersonnelRepo personnelRepo;

  @Autowired
  EventRepo eventRepo;

  @Autowired
  EventPersonnelRepo eventPersonnelRepo;

  @Autowired
  ProgramRepo programRepo;

  @Autowired
  EventProgramRepo eventProgramRepo;

  public ResponseEntity<Object> createEvent(EventRequestDTO eventRequest) {

    Event event = new Event();

    event.setName(eventRequest.getEventName());

    log.info("program id: {}", eventRequest);

    event.setDate(eventRequest.getEventDate());
    event.setBatchYear(eventRequest.getBatchYear());
    event.setTheme(eventRequest.getEventTheme());
    event.setVenue(eventRequest.getEventVenue());
    event.setThemeSongTitle(eventRequest.getEventThemeSongTitle());
    event.setThemeSong(eventRequest.getEventThemeSong());

    Event savedEvent = eventRepo.save(event);

    List<EventProgram> savedEventProgramList =  new ArrayList<>();

    for (String program : eventRequest.getPrograms()) {
      try {

        EventProgram eventProgram = new EventProgram();
        eventProgram.setEvent(savedEvent);

        Program existingProgramName = programRepo.findByName(program).orElseThrow(() -> new RuntimeException("personnel not found"));
        eventProgram.setProgram_id(existingProgramName);

       
        EventProgram savedEventProgram = eventProgramRepo.save(eventProgram);
        savedEventProgramList.add(savedEventProgram);

      } catch (Exception e) {
        throw new RuntimeException("Error saving event personnel: " + e.getMessage());
      }
    }

    if (!eventRequest.getEventPersonnelList().isEmpty()) {
      List<EventPersonnel> savedEventPersonnelList =  new ArrayList<>();
  
      for (EventPersonnelDTO eventPersonnelRequest : eventRequest.getEventPersonnelList()) {
        try {
          EventPersonnel eventPersonnel = new EventPersonnel();
          eventPersonnel.setEvent(savedEvent);
  
          Personnel existingPersonnel = personnelRepo.findByPersonnelId(eventPersonnelRequest.getPersonnelId()).orElseThrow(() -> new RuntimeException("personnel not found"));
          log.info("program id2: {}", existingPersonnel.getFullName());
          eventPersonnel.setPersonnel(existingPersonnel);
          eventPersonnel.setMessage(eventPersonnelRequest.getMessage());
          eventPersonnel.setType("Important");
  
          EventPersonnel savedEventPersonnel = eventPersonnelRepo.save(eventPersonnel);
          savedEventPersonnelList.add(savedEventPersonnel);
  
        } catch (Exception e) {
          throw new RuntimeException("Error saving event personnel: " + e.getMessage());
        }
      }
  
      event.setEventPersonnels(savedEventPersonnelList);

    }

    event.setEventProgram(savedEventProgramList);

    Event updatedEvent = eventRepo.save(event);

    return new ResponseEntity<>(updatedEvent, HttpStatus.OK);

  //   List<EventPersonnel> eventPersonnel = eventRequest.getEventPersonnelList().stream()
  //     .map(epRequest -> {

  //       EventPersonnel ep = new EventPersonnel();
  //       ep.setEventPersonnelId(epRequest.getPersonnelId());
  //       ep.setMessage(epRequest.getMessage());
  //       ep.setType("Important");
  //       return ep;

  //   }).collect(Collectors.toList());

  //   eventPersonnelRepo.saveAll(eventPersonnel);

 
  }

  public ResponseEntity<Object> getEventDetails(Integer programId, String batchYear) {
    log.info("event Request {}", programId);
    log.info("event Request {}", batchYear);
    Program existingProgram = programRepo.findByProgramId(programId).orElseThrow(() -> new RuntimeException("Program not found"));
    log.info("event Request {}", existingProgram.getName());
    
    List<Event> batchEvents = eventRepo.findByBatchYear(batchYear);

    // Event batchEvent = batchEvents

    List<EventPersonnel> eventPersonnels = eventPersonnelRepo.findByEvent(batchEvents.get(0).getEventId());

    List<EventPersonnelResponseDTO> currEP =  new ArrayList<>();

    for (EventPersonnel ep : eventPersonnels) { 
      EventPersonnelResponseDTO epDetails = new EventPersonnelResponseDTO();

      epDetails.setFullName(ep.getPersonnel().getFullName());
      epDetails.setMessage(ep.getMessage());
      currEP.add(epDetails);
    }

    EventResponseDTO eventResponse = new EventResponseDTO();

    eventResponse.setEventName(batchEvents.get(0).getName());
    eventResponse.setEventPersonnelList(currEP);

    // eventResponse.setEventDate(null);
    // Create and return the response with Event and Personnel details
    return new ResponseEntity<>(eventResponse, HttpStatus.OK);
  }

}
