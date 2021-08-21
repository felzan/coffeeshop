package com.felzan.coffeeshop.adapters.web.merchant.requestbody;

import com.felzan.coffeeshop.application.dto.MerchantDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRequest {

  String name;
  String description;
  String cnpj;
  Double latitude;
  Double longitude;
  List<MerchantWorkingHourRequest> workingHours;

  public MerchantDTO toDTO() {
    var workingHoursList = workingHours.stream()
        .map(MerchantWorkingHourRequest::toDTO)
        .collect(Collectors.toList());
    return MerchantDTO.builder()
        .name(name)
        .description(description)
        .cnpj(cnpj)
        .latitude(latitude)
        .longitude(longitude)
        .workingHours(workingHoursList)
        .build();
  }
}
