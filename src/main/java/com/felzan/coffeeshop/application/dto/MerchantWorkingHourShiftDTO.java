package com.felzan.coffeeshop.application.dto;

import static lombok.AccessLevel.PRIVATE;

import com.felzan.coffeeshop.application.models.MerchantWorkingHourShift;
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
public class MerchantWorkingHourShiftDTO extends BaseDTO {

  LocalTime begin;
  LocalTime end;

  public MerchantWorkingHourShift toModel() {
    return MerchantWorkingHourShift.builder()
        .begin(begin)
        .end(end)
        .build();
  }
}
