package com.alchives.alchiveserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "[achievement]")
public class Achievement{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "achievement_id")
  private Integer achievementsId;

  @Column(name = "alumni_id")
  private Integer alumniId;

  @Column(name = "text")
  private String text;

  @Column(name = "date")
  private String date;
}
