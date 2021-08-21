package com.felzan.coffeeshop.adapters.web.user.requestbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  String username;
  String password;
  Long whiteLabelId;
  String name;
  String email;

}