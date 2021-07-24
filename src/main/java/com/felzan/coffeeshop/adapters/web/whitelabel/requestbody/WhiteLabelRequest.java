package com.felzan.coffeeshop.adapters.web.whitelabel.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.WhiteLabelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhiteLabelRequest {

  String name;
  String description;

  public WhiteLabelDTO toDTO() {
    return WhiteLabelDTO.builder()
        .name(name)
        .description(description)
        .build();
  }
}
