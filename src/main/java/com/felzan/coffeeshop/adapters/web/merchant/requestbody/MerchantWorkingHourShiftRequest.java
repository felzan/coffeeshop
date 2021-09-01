package com.felzan.coffeeshop.adapters.web.merchant.requestbody;

import com.felzan.coffeeshop.application.dto.MerchantWorkingHourShiftDTO;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantWorkingHourShiftRequest {

  @NotNull LocalTime begin;
  @NotNull LocalTime end;

  public MerchantWorkingHourShiftDTO toDTO() {
    return MerchantWorkingHourShiftDTO.builder()
        .begin(begin)
        .end(end)
        .build();
  }
}
