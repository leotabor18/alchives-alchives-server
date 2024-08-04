package com.alchives.alchiveserver.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance
@Entity
public class Event {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Integer eventId;

  @Column(name = "name")
	private String name;

  @Column(name = "theme")
	private String theme;

  @Column(name = "vanue")
	private String venue;

  @Column(name = "date")
	private Date date;

  @Column(name = "batch_year")
	private String batchYear;

  @Column(name = "theme_song")
	private String themeSong;

  @ManyToMany
	@JoinTable(
		name = "event_program", 
  	joinColumns = @JoinColumn(name = "event_id"), 
  	inverseJoinColumns = @JoinColumn(name = "program_id"))
	private List<Program> programs;

  @OneToMany(mappedBy = "event")
	private List<EventPersonnel> eventPersonnels;

  @OneToMany(mappedBy = "event")
	private List<BatchPicture> batchPictures;
}
