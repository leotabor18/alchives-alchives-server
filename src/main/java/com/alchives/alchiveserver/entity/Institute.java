package com.alchives.alchiveserver.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance
@Entity
@Table(name = "[institute]")
public class Institute extends Abstract implements IEntity{
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institute_id")
	private Integer instituteId;
  
  @Column(name = "name", unique = true)
	private String name;

	@Column(name = "description")
	private String description;

  @OneToMany(mappedBy = "institute")
	private List<Program> programs;
}
