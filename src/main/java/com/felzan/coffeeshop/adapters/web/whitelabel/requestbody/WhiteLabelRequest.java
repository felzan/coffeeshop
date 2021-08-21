package com.felzan.coffeeshop.adapters.web.whitelabel.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhiteLabelRequest {

  String name;
  String description;

}
