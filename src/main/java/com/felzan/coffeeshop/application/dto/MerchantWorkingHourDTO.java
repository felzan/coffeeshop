package com.felzan.coffeeshop.application.dto;


import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.MerchantWorkingHour;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MerchantWorkingHourDTO extends BaseDTO {

  Set<DayOfWeek> days;
  String description;
  List<MerchantWorkingHourShiftDTO> shifts;

  public MerchantWorkingHour toModel() {
    var shiftList = shifts.stream()
        .map(MerchantWorkingHourShiftDTO::toModel)
        .collect(Collectors.toList());
    return MerchantWorkingHour.builder()
        .days(days)
        .description(description)
        .shifts(shiftList)
        .build();
  }
}
