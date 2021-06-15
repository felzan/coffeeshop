package com.felzan.coffeeshop.adapters.mysql;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @CreatedDate
  @Column(updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
