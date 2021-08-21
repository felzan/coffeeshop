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
public class Merchant extends BaseModel {

  String name;
  String description;
  String cnpj;
  Double latitude;
  Double longitude;
  @Builder.Default
  List<Product> products = new ArrayList<>();
  @Builder.Default
  List<Category> categories = new ArrayList<>();
  @Builder.Default
  List<Order> orders = new ArrayList<>();
  @Builder.Default
  List<MerchantWorkingHour> workingHours = new ArrayList<>();
}
