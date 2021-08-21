package com.felzan.coffeeshop.application.models;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {

  String username;
  String password;
  String name;
  String email;
  String status;
  String token;
  WhiteLabel whiteLabel;
  @Builder.Default
  List<Order> orders = new ArrayList<>();
}
