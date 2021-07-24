package com.felzan.coffeeshop.adapters.web.merchant.requestbody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.felzan.coffeeshop.application.dto.MerchantWorkingHourShiftDTO;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantWorkingHourShiftRequest {

  LocalTime begin;
  LocalTime end;

  public MerchantWorkingHourShiftDTO toDTO() {
    return MerchantWorkingHourShiftDTO.builder()
        .begin(begin)
        .end(end)
        .build();
  }
}
