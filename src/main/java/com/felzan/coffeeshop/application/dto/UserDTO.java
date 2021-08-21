package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = PRIVATE)
public class UserDTO extends BaseDTO {

  String username;
  String password;
  String email;
  String name;
  String status;
  String token;
  Long whiteLabelId;
  WhiteLabelDTO whiteLabelDTO;

}
