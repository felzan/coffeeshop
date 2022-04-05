package com.felzan.coffeeshop.application.models;


import static lombok.AccessLevel.PRIVATE;

import java.time.LocalTime;
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
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantWorkingHourShift extends BaseModel {

  LocalTime begin;
  LocalTime end;
}
