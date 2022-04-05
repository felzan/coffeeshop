package com.felzan.coffeeshop.adapters.web.merchant.requestbody;

import com.felzan.coffeeshop.application.dto.MerchantWorkingHourDTO;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantWorkingHourRequest {

  @NotNull Set<DayOfWeek> days;
  String description;
  @NotNull List<MerchantWorkingHourShiftRequest> shifts;

  public MerchantWorkingHourDTO toDTO() {
    var shiftList = shifts.stream()
        .map(MerchantWorkingHourShiftRequest::toDTO)
        .collect(Collectors.toList());
    return MerchantWorkingHourDTO.builder()
        .days(days)
        .description(description)
        .shifts(shiftList)
        .build();
  }
}
