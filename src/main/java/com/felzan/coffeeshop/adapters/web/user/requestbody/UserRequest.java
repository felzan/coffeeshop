package com.felzan.coffeeshop.adapters.web.user.requestbody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  @NotBlank String username;
  @NotBlank String password;
  @NotNull @Positive Long whiteLabelId;
  String name;
  String email;

}