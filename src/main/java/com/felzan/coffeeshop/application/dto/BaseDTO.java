package com.felzan.coffeeshop.application.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO {

  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
