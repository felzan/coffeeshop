package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.WhiteLabel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class WhiteLabelDTO extends BaseDTO {

  String name;
  String description;

  public WhiteLabel toModel() {
    return WhiteLabel.builder()
        .name(name)
        .description(description)
        .build();
  }
}
