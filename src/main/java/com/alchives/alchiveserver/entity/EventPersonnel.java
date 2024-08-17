package com.alchives.alchiveserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance
@Entity
@Table(name = "[event_personnel]")
public class EventPersonnel extends Abstract implements IEntity {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_personnel_id")
	private Integer eventPersonnelId;

  @Column(name = "type")
	private String type;

  @Column(name = "message")
	private String message;

  @ManyToOne	
  @JoinColumn(name = "personnel_id")
	private Personnel personnel;

  @JsonIgnore
  @ManyToOne	
  @JoinColumn(name = "event_id")
	private Event event;
}
