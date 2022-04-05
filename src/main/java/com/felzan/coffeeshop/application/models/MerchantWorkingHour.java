package com.felzan.coffeeshop.application.models;

import static lombok.AccessLevel.PRIVATE;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
public class MerchantWorkingHour extends BaseModel {

  Set<DayOfWeek> days;
  String description;
  @Builder.Default
  List<MerchantWorkingHourShift> shifts = new ArrayList<>();
}
