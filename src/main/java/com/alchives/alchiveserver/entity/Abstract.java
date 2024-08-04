package com.alchives.alchiveserver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class Abstract {
  @Column(name = "who_added")
  @CreatedBy
  protected Integer whoAdded;

  @Column(name = "who_updated", nullable = true)
  @LastModifiedBy
  protected Integer whoUpdated;

  @Column(name = "when_added")
  @CreationTimestamp
  protected Date whenAdded;

  @UpdateTimestamp
  protected Date ts;
}
