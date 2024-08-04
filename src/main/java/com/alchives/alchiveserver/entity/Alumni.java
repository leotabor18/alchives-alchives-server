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
@Table(name = "[alumni]")
public class Alumni extends Abstract implements IEntity {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alumni_id")
	private Integer alumniId;

	@Column(name = "student_number", unique = true)
	private String studentNumber;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

  @Column(name = "suffix")
	private String suffix;

  @Column(name = "image")
	private String image;

  @Column(name = "batch_year")
	private String batchYear;

  @Column(name = "quotes")
	private String quotes;

  @Column(name = "award")
	private String award;

	@Column(name = "status")
	private String status;

	@Column(name = "program_id")
	private Integer programId;
}
