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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance
@Entity
@Table(name = "[event]")
public class Event extends Abstract implements IEntity {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Integer eventId;

  @Column(name = "name")
	private String name;

  @Column(name = "theme")
	private String theme;

  @Column(name = "venue")
	private String venue;

  @Column(name = "date")
	private Date date;

  @Column(name = "batch_year")
	private String batchYear;

  @Column(name = "theme_song_title")
	private String themeSongTitle;

  @Column(name = "theme_song")
	private String themeSong;

  // @ManyToOne
	// @JoinTable(
	// 	name = "event_program", 
  // 	joinColumns = @JoinColumn(name = "event_id"), 
  // 	inverseJoinColumns = @JoinColumn(name = "program_id"))
	// private Program program;

  @ManyToMany(mappedBy = "event")
	private List<EventProgram> eventProgram;

  @OneToMany(mappedBy = "event")
	private List<EventPersonnel> eventPersonnels;

  // @OneToMany(mappedBy = "event")
	// private List<BatchPicture> batchPictures;
}
