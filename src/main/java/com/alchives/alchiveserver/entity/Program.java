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
@Table(name = "[program]")
public class Program extends Abstract implements IEntity {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "program_id")
	private Integer programId;

  @Column(name = "name", unique = true)
	private String name;

	@Column(name = "description")
	private String description;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "institute_id")
	private Institute institute;
}
