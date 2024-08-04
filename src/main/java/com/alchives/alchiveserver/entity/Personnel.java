package com.alchives.alchiveserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance
@Entity
@Table(name = "[personnel]")
public class Personnel extends Abstract implements IEntity {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personnel_id")
	private Integer personnelId;

  @Column(name = "full_name")
	private String fullName;

  @Column(name = "prefix")
	private String prefix;

  @Column(name = "suffix")
	private String suffix;

  @Column(name = "title")
	private String title;

  @Column(name = "department")
	private String department;

  @Column(name = "position")
	private String position;

  @Column(name = "role")
	private String role;

  @Column(name = "profile_pic")
	private String profilePic;
}
